package com.customerservice.customerservice.main;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import java.text.ParseException;
import java.util.List;

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
    @PostMapping("/modifyCustomer")
    public ResponseEntity modifyCustomer (@RequestBody JSONObject customerToModify){
        return customerService.modifyCustomer(customerToModify);
    }
    @GetMapping("/showCustomersList")
    public List returnCustomersList (@RequestBody JSONObject Customer){
        return customerService.returnCustomersList(Customer);
    }
    @PostMapping("/customerSearchByParams")
    public List customerByID(@RequestBody JSONObject Customer){
        return customerService.searchCustomer(Customer);
    }

}
