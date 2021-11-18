package com.insuranceapplication.userservice.main;
import com.insuranceapplication.userservice.model.Users;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseBody
    @PostMapping("/verify")
    public ResponseEntity verifyUserLogin(@RequestBody Users user) {
        return userService.verifyUserLogin(user);
    }

    @GetMapping("/showStatus")
    public String showStatus() {
        return "Server is online.";
    }
}