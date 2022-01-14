package claimservice;

import claimservice.models.Bills;
import claimservice.models.Claims;
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
    public void createClaim(@RequestBody Claims claim) {
        claimService.createClaim(claim);
    }

    @PostMapping("/updateclaim")
    @ResponseBody
    public void updateClaim(@RequestBody Claims claim) {
        claimService.updateClaim(claim);
    }

    @PostMapping("/removeclaim")
    @ResponseBody
    public void removeClaim(@RequestBody Claims claim) {
        claimService.removeClaim(claim);
    }

    @PostMapping("/getclaims")
    @ResponseBody
    public void getClaim(@RequestBody InsuredObjects driver) {
        claimService.getClaims(driver);
    }

    @PostMapping("/createbill")
    @ResponseBody
    public void createBill(@RequestBody Bills bill) {
        claimService.createBill(bill);
    }

    @PostMapping("/updatebill")
    @ResponseBody
    public void updateBill(@RequestBody Bills bill) {
        claimService.updateBill(bill);
    }

    @PostMapping("/removebill")
    @ResponseBody
    public void removeClaim(@RequestBody Bills bill) {
        claimService.removeBill(bill);
    }

    @PostMapping("/getbills")
    @ResponseBody
    public List getBills(@RequestBody Claims claim) {
        return claimService.getBills(claim);
    }
    @PostMapping("/createvictim")
    @ResponseBody
    public void createvictim(@RequestBody Victims victim) {
        claimService.createVictim(victim);
    }

    @PostMapping("/updatevictim")
    @ResponseBody
    public void updateVictim(@RequestBody Victims victim) {
        claimService.updateVictim(victim);
    }

    @PostMapping("/removevictim")
    @ResponseBody
    public void removeVictim(@RequestBody Victims victim) {
        claimService.removeVictim(victim);
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
