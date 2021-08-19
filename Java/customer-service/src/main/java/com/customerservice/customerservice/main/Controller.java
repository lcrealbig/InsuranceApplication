package com.customerservice.customerservice.main;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
@RestController

public class Controller {
    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @PostMapping("/addCustomer")
    public ResponseEntity addCustomer(@RequestBody JSONObject customerToAdd) throws ParseException {
        return customerService.verifyCustomerPeselAndBirth(customerToAdd);
    }
    @DeleteMapping("/deleteCustomer")
    public ResponseEntity deleteCustomer(@RequestBody JSONObject customerToDelete){
        return customerService.deleteCustomer(customerToDelete);
    }
}
