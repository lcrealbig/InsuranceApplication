package claimservice.services;

import claimservice.globals.Variables;
import claimservice.models.Bills;
import claimservice.models.Claims;
import claimservice.models.InsuredObjects;
import claimservice.models.Victims;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class ClaimService {
    @Autowired
    EurekaClient eurekaClient;
    private RestTemplate template = new RestTemplate();

    public ResponseEntity createClaim(Claims claim) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createclaim", claim, String.class);

    }

    public ResponseEntity updateClaim(Claims claim) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updateclaim", claim, Claims.class);

    }

    public ResponseEntity deleteClaim(Claims claim) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/deleteclaim", claim, Claims.class);
    }

    public ResponseEntity<List> getClaims(InsuredObjects driver) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getclaims", driver, List.class);
    }

    public ResponseEntity createBill(Bills bill) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createbill", bill, String.class);
    }

    public ResponseEntity updateBill(Bills bill) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updatebill", bill, Bills.class);

    }

    public ResponseEntity deleteBill(Bills bill) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/deletebill", bill, Bills.class);
    }

    public ResponseEntity<List> getBills(Claims claim) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getbills", claim, List.class);
    }

    public ResponseEntity createVictim(Victims victim) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createvictim", victim, String.class);
    }

    public ResponseEntity updateVictim(Victims victim) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updatevictim", victim, Victims.class);

    }

    public ResponseEntity deleteVictim(Victims victim) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/deletevictim", victim, Victims.class);
    }

    public ResponseEntity<List> getVictims(Bills bill) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getvictims", bill, List.class);
    }


}
