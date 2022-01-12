package claimservice;

import claimservice.models.Bills;
import claimservice.models.Claim;
import claimservice.models.InsuredObjects;
import claimservice.models.Victims;
import claimservice.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class Controller {
    @Autowired
    private ClaimService claimService;

    @PostMapping("/createclaim")
    @ResponseBody
    public void createClaim(@RequestBody Claim claim) {
        claimService.createClaim(claim);
    }

    @PostMapping("/updateclaim")
    @ResponseBody
    public void updateClaim(@RequestBody Claim claim) {
        claimService.updateClaim(claim);
    }

    @PostMapping("/removeclaim")
    @ResponseBody
    public void removeClaim(@RequestBody Claim claim) {
        claimService.removeClaim(claim);
    }

    @PostMapping("/getclaims")
    @ResponseBody
    public void getClaim(@RequestBody InsuredObjects driver) {
        claimService.getClaims(driver);
    }

    @PostMapping("/createbill")
    @ResponseBody
    public void createBill(@RequestBody Bills bills) {
        claimService.createBill(bills);
    }

    @PostMapping("/updatebill")
    @ResponseBody
    public void updateBill(@RequestBody Bills bills) {
        claimService.updateBill(bills);
    }

    @PostMapping("/removebill")
    @ResponseBody
    public void removeClaim(@RequestBody Bills bills) {
        claimService.removeBill(bills);
    }

    @PostMapping("/getbills")
    @ResponseBody
    public List getBills(@RequestBody Claim claim) {
        return claimService.getBills(claim);
    }
    @PostMapping("/createvictim")
    @ResponseBody
    public void createvictim(@RequestBody Victims victims) {
        claimService.createVictim(victims);
    }

    @PostMapping("/updatevictim")
    @ResponseBody
    public void updateVictim(@RequestBody Victims victims) {
        claimService.updateVictim(victims);
    }

    @PostMapping("/removevictim")
    @ResponseBody
    public void removeVictim(@RequestBody Victims victims) {
        claimService.removeVictim(victims);
    }

    @PostMapping("/getvictims")
    @ResponseBody
    public List getVictims(@RequestBody Bills bill) {
        return claimService.getVictims(bill);
    }

    @GetMapping("/serviceStatus")
    @ResponseBody
    public ResponseEntity getStatus() {
        return ResponseEntity.ok().body("claim-service is [ONLINE]");
    }

}
