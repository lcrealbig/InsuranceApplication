package claimservice.services;

import claimservice.globals.Variables;
import claimservice.models.Bill;
import claimservice.models.Claim;
import claimservice.models.InsuredObjects;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class ClaimService {
    @Autowired
    EurekaClient eurekaClient;
    private RestTemplate template = new RestTemplate();

    public void createClaim(Claim claim) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createclaim", claim, String.class);
    }

    public void updateClaim(Claim claim) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updateclaim", claim, Claim.class);

    }

    public void removeClaim(Claim claim) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/removeclaim", claim, Claim.class);
    }

    public void getClaims(InsuredObjects driver) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getclaims", driver, List.class);
    }

    public void createBill(Bill bill) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createbill", bill, String.class);
    }

    public void updateBill(Bill bill) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updatebill", bill, Bill.class);

    }

    public void removeBill(Bill bill) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/removebill", bill, Bill.class);
    }

    public List getBills(Claim claim) {
        return (List) template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getclaims", claim, List.class);
    }


}
