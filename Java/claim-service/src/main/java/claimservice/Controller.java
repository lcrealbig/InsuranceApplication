package claimservice;

import claimservice.models.Bill;
import claimservice.models.Claim;
import claimservice.models.InsuredObjects;
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
    public void createBill(@RequestBody Bill bill) {
        claimService.createBill(bill);
    }

    @PostMapping("/updatebill")
    @ResponseBody
    public void updateBill(@RequestBody Bill bill) {
        claimService.updateBill(bill);
    }

    @PostMapping("/removebill")
    @ResponseBody
    public void removeClaim(@RequestBody Bill bill) {
        claimService.removeBill(bill);
    }

    @PostMapping("/getbills")
    @ResponseBody
    public List getBills(@RequestBody Claim claim) {
        return claimService.getBills(claim);
    }

    @GetMapping("/serviceStatus")
    @ResponseBody
    public ResponseEntity getStatus() {
        return ResponseEntity.ok().body("claim-service is [ONLINE]");
    }

}
