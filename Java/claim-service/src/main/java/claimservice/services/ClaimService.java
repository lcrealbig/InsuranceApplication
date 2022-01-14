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

    public ResponseEntity createClaim(Claims claims) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createclaim", claims, String.class);

    }

    public ResponseEntity updateClaim(Claims claims) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updateclaim", claims, Claims.class);

    }

    public ResponseEntity deleteClaim(Claims claims) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/deleteclaim", claims, Claims.class);
    }

    public ResponseEntity<List> getClaims(InsuredObjects driver) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getclaims", driver, List.class);
    }

    public ResponseEntity createBill(Bills bills) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createbill", bills, String.class);
    }

    public ResponseEntity updateBill(Bills bills) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updatebill", bills, Bills.class);

    }

    public ResponseEntity deleteBill(Bills bills) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/deletebill", bills, Bills.class);
    }

    public ResponseEntity<List> getBills(Claims claims) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getbills", claims, List.class);
    }

    public ResponseEntity createVictim(Victims victims) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/createvictim", victims, String.class);
    }

    public ResponseEntity updateVictim(Victims victims) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/updatevictim", victims, Victims.class);

    }

    public ResponseEntity deleteVictim(Victims victims) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/deletevictim", victims, Victims.class);
    }

    public ResponseEntity<List> getVictims(Bills bill) {
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl() + "/getvictims", bill, List.class);
    }


}
