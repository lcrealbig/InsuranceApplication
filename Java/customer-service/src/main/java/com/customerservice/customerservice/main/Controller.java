package com.customerservice.customerservice.main;

import com.customerservice.customerservice.globalconstants.DeployConstants;
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

    private final String appOrigins = DeployConstants.herokuLink;

    @CrossOrigin(origins = appOrigins)
    @ResponseBody
    @PostMapping("/addCustomer")
    public ResponseEntity addCustomer(@RequestBody JSONObject customerToAdd) throws ParseException {
        return customerService.verifyCustomerPeselAndBirth(customerToAdd);
    }

    @CrossOrigin(origins = appOrigins)
    @DeleteMapping("/deleteCustomer")
    public ResponseEntity deleteCustomer(@RequestBody JSONObject customerToDelete){
        return customerService.deleteCustomer(customerToDelete);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/modifyCustomer")
    public ResponseEntity modifyCustomer (@RequestBody JSONObject customerToModify){
        return customerService.modifyCustomer(customerToModify);
    }

    @CrossOrigin(origins = appOrigins)
    @GetMapping("/showCustomersList")
    public List returnCustomersList (@RequestBody JSONObject Customer){
        return customerService.returnCustomersList();
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/customerSearchByID")
    public List<Customers> customerByID(@RequestBody JSONObject Customer){
        return customerService.searchCustomerById(Customer);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/customerSearchByPesel")
    public List<Customers> customerByPesel(@RequestBody JSONObject Customer){
        return customerService.searchCustomerByPesel(Customer);
    }

    @CrossOrigin(origins = appOrigins)
    @PostMapping("/customerSearchByName")
    public List customerByName(@RequestBody JSONObject Customer){
        return customerService.searchCustomerByName(Customer);
    }

}
