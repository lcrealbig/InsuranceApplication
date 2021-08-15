package com.customerservice.customerservice.main;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
//TO DO.

public class Controller {
    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @PostMapping("/addCustomer")
    public ResponseEntity addCustomer(@RequestBody JSONObject jsonObject) throws ParseException {
        return customerService.verifyCustomerPeselAndBirth(jsonObject);
    }
}
