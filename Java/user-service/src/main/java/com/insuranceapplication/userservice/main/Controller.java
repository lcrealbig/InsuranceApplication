package com.insuranceapplication.userservice.main;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/verify")
    public ResponseEntity verifyUserLogin(@RequestBody JSONObject jsonObject) {
        return userService.verifyUserLogin(jsonObject);
    }

    @GetMapping("/showStatus")
    public String showStatus() {
        return "Server is online.";
    }
}