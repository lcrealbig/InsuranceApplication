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
    public ResponseEntity createClaim(@RequestBody Claims claim) {
        return claimService.createClaim(claim);
    }

    @PostMapping("/updateclaim")
    @ResponseBody
    public ResponseEntity updateClaim(@RequestBody Claims claim) {
        return claimService.updateClaim(claim);
    }

    @PostMapping("/deleteclaim")
    @ResponseBody
    public ResponseEntity deleteClaim(@RequestBody Claims claim) {
        return claimService.deleteClaim(claim);
    }

    @PostMapping("/getclaims")
    @ResponseBody
    public ResponseEntity<List> getClaims(@RequestBody InsuredObjects driver) {
        return claimService.getClaims(driver);
    }

    @PostMapping("/createbill")
    @ResponseBody
    public ResponseEntity createBill(@RequestBody Bills bill) {
        return claimService.createBill(bill);
    }

    @PostMapping("/updatebill")
    @ResponseBody
    public ResponseEntity updateBill(@RequestBody Bills bill) {
        return claimService.updateBill(bill);
    }

    @PostMapping("/deletebill")
    @ResponseBody
    public ResponseEntity deleteClaim(@RequestBody Bills bill) {
        return claimService.deleteBill(bill);
    }

    @PostMapping("/getbills")
    @ResponseBody
    public ResponseEntity<List> getBills(@RequestBody Claims claim) {
        return claimService.getBills(claim);
    }

    @PostMapping("/createvictim")
    @ResponseBody
    public ResponseEntity createvictim(@RequestBody Victims victim) {
        return claimService.createVictim(victim);
    }

    @PostMapping("/updatevictim")
    @ResponseBody
    public ResponseEntity updateVictim(@RequestBody Victims victim) {
        return claimService.updateVictim(victim);
    }

    @PostMapping("/deletevictim")
    @ResponseBody
    public ResponseEntity deleteVictim(@RequestBody Victims victim) {
        return claimService.deleteVictim(victim);
    }

    @PostMapping("/getvictims")
    @ResponseBody
    public ResponseEntity<List> getVictims(@RequestBody Bills bill) {
        return claimService.getVictims(bill);
    }

    @GetMapping("/serviceStatus")
    @ResponseBody
    public ResponseEntity getStatus() {
        return ResponseEntity.ok().body("claim-service is [ONLINE]");
    }

}
