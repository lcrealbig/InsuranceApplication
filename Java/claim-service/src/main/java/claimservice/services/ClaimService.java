package claimservice.services;

import claimservice.globals.Variables;
import claimservice.models.Bills;
import claimservice.models.Claim;
import claimservice.models.InsuredObjects;
import claimservice.models.Victims;
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

    public void createBill(Bills bills) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createbill", bills, String.class);
    }

    public void updateBill(Bills bills) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updatebill", bills, Bills.class);

    }

    public void removeBill(Bills bills) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/removebill", bills, Bills.class);
    }

    public List getBills(Claim claim) {
        return (List) template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getbills", claim, List.class);
    }
    public void createVictim(Victims victims) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createvictim", victims, Victims.class);
    }

    public void updateVictim(Victims victims) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updatevictim", victims, Victims.class);

    }

    public void removeVictim(Victims victims) {
        template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/removevictim", victims, Victims.class);
    }

    public List getVictims(Bills bill) {
        return (List) template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getvictims", bill, List.class);
    }


}
