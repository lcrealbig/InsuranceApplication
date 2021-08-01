package com.insuranceapplication.userservice.main;


import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@RestController
public class Controller {

    private final UserService userService;

    @PersistenceContext
    private EntityManager em;

    public Controller(UserService userService, EntityManager em) {
        this.userService = userService;
        this.em = em;
    }

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
