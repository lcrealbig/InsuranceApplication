package claimservice;

import claimservice.models.Claim;
import claimservice.models.InsuredObjects;
import claimservice.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class Controller {
    @Autowired
    private ClaimService claimService;

    @PostMapping("/createclaim")
    @ResponseBody
    public void createClaim(@RequestBody Claim claim){
        claimService.createClaim(claim);
    }

    @PostMapping("/updateclaim")
    @ResponseBody
    public void updateClaim(@RequestBody Claim claim){
        claimService.updateClaim(claim);
    }

    @PostMapping("/removeclaim")
    @ResponseBody
    public void removeClaim(@RequestBody Claim claim){
        claimService.removeClaim(claim);
    }
    @PostMapping("/removeclaims")
    @ResponseBody
    public void getClaim(@RequestBody InsuredObjects driver){
        claimService.getClaims(driver);
    }

    @GetMapping("/serviceStatus")
    @ResponseBody
    public ResponseEntity getStatus() {
        return ResponseEntity.ok().body("claim-service is [ONLINE]");
    }

}
