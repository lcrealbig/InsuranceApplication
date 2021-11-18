package com.customerservice.customerservice.main;

import com.customerservice.customerservice.globals.Constants;
import com.customerservice.customerservice.model.Customers;
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

    @CrossOrigin(origins = Constants.origin)
    @ResponseBody
    @PostMapping("/addCustomer")
    public ResponseEntity addCustomer(@RequestBody JSONObject customerToAdd) throws ParseException {
        return customerService.verifyCustomerPeselAndBirth(customerToAdd);
    }

    @CrossOrigin(origins = Constants.origin)
    @DeleteMapping("/deleteCustomer")
    public ResponseEntity deleteCustomer(@RequestBody JSONObject customerToDelete){
        return customerService.deleteCustomer(customerToDelete);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/modifyCustomer")
    public ResponseEntity modifyCustomer (@RequestBody JSONObject customerToModify){
        return customerService.modifyCustomer(customerToModify);
    }

    @CrossOrigin(origins = Constants.origin)
    @GetMapping("/showCustomersList")
    public List returnCustomersList (@RequestBody JSONObject Customer){
        return customerService.returnCustomersList();
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/customerSearchByID")
    public List<Customers> customerByID(@RequestBody JSONObject Customer){
        return customerService.searchCustomerById(Customer);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/customerSearchByPesel")
    public List<Customers> customerByPesel(@RequestBody JSONObject Customer){
        return customerService.searchCustomerByPesel(Customer);
    }

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("/customerSearchByName")
    public List customerByName(@RequestBody JSONObject Customer){
        return customerService.searchCustomerByName(Customer);
    }

}
