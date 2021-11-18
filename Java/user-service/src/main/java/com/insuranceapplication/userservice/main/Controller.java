package com.insuranceapplication.userservice.main;
import com.insuranceapplication.userservice.globals.Constants;
import com.insuranceapplication.userservice.model.Users;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/verify")
    public ResponseEntity verifyUserLogin(@RequestBody Users user) {
        return userService.verifyUserLogin(user);
    }

    @GetMapping("/showStatus")
    public String showStatus() {
        return "User service is online.";
    }
}