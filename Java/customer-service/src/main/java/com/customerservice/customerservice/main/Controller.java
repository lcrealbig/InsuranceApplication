package com.customerservice.customerservice.main;

import com.customerservice.customerservice.model.Customers;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController

public class Controller {
    @Autowired
    private CustomerService customerService;

    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addCustomer")
    public ResponseEntity addCustomer(@RequestBody JSONObject customerToAdd) throws ParseException {
        return customerService.verifyCustomerPeselAndBirth(customerToAdd);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteCustomer")
    public ResponseEntity deleteCustomer(@RequestBody JSONObject customerToDelete){
        return customerService.deleteCustomer(customerToDelete);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/modifyCustomer")
    public ResponseEntity modifyCustomer (@RequestBody JSONObject customerToModify){
        return customerService.modifyCustomer(customerToModify);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/showCustomersList")
    public List returnCustomersList (@RequestBody JSONObject Customer){
        return customerService.returnCustomersList();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/customerSearchByID")
    public List<Customers> customerByID(@RequestBody JSONObject Customer){
        return customerService.searchCustomerById(Customer);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/customerSearchByPesel")
    public List<Customers> customerByPesel(@RequestBody JSONObject Customer){
        return customerService.searchCustomerByPesel(Customer);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/customerSearchByName")
    public List customerByName(@RequestBody JSONObject Customer){
        return customerService.searchCustomerByName(Customer);
    }

}
