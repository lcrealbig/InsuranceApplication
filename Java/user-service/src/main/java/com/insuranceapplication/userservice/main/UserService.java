package com.insuranceapplication.userservice.main;
        import com.insuranceapplication.userservice.model.Users;
        import com.netflix.discovery.EurekaClient;
        import org.json.simple.JSONObject;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.client.RestTemplate;

        import java.util.List;

@Service
public class UserService {

    @Autowired
    private EurekaClient eurekaClient;

    public ResponseEntity verifyUserLogin(@RequestBody Users user) {
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication("DATABASE").getInstances().get(0).getHomePageUrl() + "/verify", user, Users.class);
        return ResponseEntity.ok().body(response);
    }
}