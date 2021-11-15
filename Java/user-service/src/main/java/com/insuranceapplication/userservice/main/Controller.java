package com.insuranceapplication.userservice.main;
import com.insuranceapplication.userservice.globalconstants.DeployConstants;
import com.insuranceapplication.userservice.model.Users;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private UserService userService;

    private final String appOrigins = DeployConstants.herokuLink;

    @CrossOrigin(origins = appOrigins)
    @ResponseBody
    @PostMapping("/verify")
    public ResponseEntity verifyUserLogin(@RequestBody Users user) {
        return userService.verifyUserLogin(user);
    }

    @CrossOrigin(origins = appOrigins)
    @GetMapping("/showStatus")
    public String showStatus() {
        return "User service is online.";
    }
}