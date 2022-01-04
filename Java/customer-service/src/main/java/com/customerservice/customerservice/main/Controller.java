package com.customerservice.customerservice.main;

import com.customerservice.customerservice.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class Controller {
    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @PostMapping("/createcustomer")
    public ResponseEntity createCustomer(@RequestBody Customers customer) {
        return customerService.createCustomer(customer);
    }

    @PostMapping("/modifycustomer")
    public ResponseEntity modifyCustomer(@RequestBody Customers customer) {
        return customerService.modifyCustomer(customer);
    }

    @PostMapping("/searchcustomers")
    public ResponseEntity searchCustomers(@RequestBody Customers customer) {
        return customerService.searchCustomers(customer);
    }

    @PostMapping("/deletecustomer")
    public ResponseEntity deleteCustomer(@RequestBody Customers customer) {
        return customerService.deleteCustomer(customer);
    }

    @GetMapping("/serviceStatus")
    @ResponseBody
    public ResponseEntity getStatus() {
        return ResponseEntity.ok().body("customer-service is [ONLINE]");
    }

}
