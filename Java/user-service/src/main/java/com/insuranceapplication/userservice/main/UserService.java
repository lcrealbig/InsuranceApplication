package com.insuranceapplication.userservice.main;
        import com.insuranceapplication.userservice.globals.Variables;
        import com.insuranceapplication.userservice.model.authentication.Role;
        import com.insuranceapplication.userservice.model.authentication.User;
        import com.netflix.discovery.EurekaClient;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.core.authority.SimpleGrantedAuthority;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.client.RestTemplate;

        import java.util.ArrayList;
        import java.util.Collection;
        import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private EurekaClient eurekaClient;

    public ResponseEntity verifyUserLogin(@RequestBody User user) {
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/verify", user, User.class);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(username);
        user = (User) new RestTemplate().postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/finduserbyname", user, User.class).getBody();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),"{bcrypt}"+user.getPassword(),authorities);
    }
}