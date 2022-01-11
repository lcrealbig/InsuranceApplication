package claimservice.services;

import claimservice.globals.Variables;
import claimservice.models.Claim;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ClaimService {
    @Autowired
    EurekaClient eurekaClient;
    private RestTemplate template = new RestTemplate();
    
    public void createClaim(Claim claim){
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createclaim", claim, String.class);    }

}
