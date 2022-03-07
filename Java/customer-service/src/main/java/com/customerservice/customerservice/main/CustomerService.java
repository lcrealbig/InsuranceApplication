package com.customerservice.customerservice.main;

import com.customerservice.customerservice.globals.Variables;
import com.customerservice.customerservice.methods.Utils;
import com.customerservice.customerservice.model.Customer;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    EurekaClient eurekaClient;

    public ResponseEntity verifyCustomerPeselAndBirth(Customer customer) {
        String pesel = customer.getPesel();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String formattedDate = formatter.format(customer.getBirthDate());
        String substrPesel = pesel.substring(0, 6);
        StringBuilder bornAfter2000sPesel = new StringBuilder(substrPesel);

        if (pesel.length() != 11) {
            return ResponseEntity.badRequest().body("Incorrect pesel length.");
        }
        int controlNumber = Integer.valueOf(pesel.substring(10));
        int checkSum = 0;
        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int[] peselArray = new int[11];
        char[] peselToCharArray = pesel.toCharArray();

        if (pesel.startsWith("0") && pesel.charAt(2) == '2') {
            substrPesel = bornAfter2000sPesel.replace(2, 3, "0").toString();
        }
        if (pesel.startsWith("0") && pesel.charAt(2) == '3') {
            substrPesel = bornAfter2000sPesel.replace(2, 3, "1").toString();
        }
        if (!substrPesel.equals(formattedDate)) {
            return ResponseEntity.badRequest().body("Birth date and pesel does not match.");
        }

        for (int i = 0; i < 10; i++) {
            peselArray[i] = Character.getNumericValue(peselToCharArray[i]);
            checkSum = weights[i] * peselArray[i] + checkSum;
        }
        checkSum = 10 - (checkSum % 10) % 10;

        if (controlNumber != checkSum) {
            return ResponseEntity.badRequest().body("Pesel is incorrect");
        }
        ArrayList<Customer> customers = (ArrayList<Customer>) Utils.mapToList((List<LinkedHashMap>) getAllCustomers().getBody(), Customer.class);
        for(Customer existingCustomer : customers){
            if(existingCustomer.getPesel().equals(customer.getPesel()) && !existingCustomer.getId().equals(customer.getId())){
                return ResponseEntity.badRequest().body("Pesel already exist in database");
            }
        }


        return ResponseEntity.ok().body(customer);
    }

    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        ResponseEntity customerVerification;
        customerVerification = verifyCustomerPeselAndBirth(customer);
        if (customerVerification.getStatusCode().value() == 200) {
            RestTemplate template = new RestTemplate();
            return template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                    .getInstances().get(0).getHomePageUrl()+"/createcustomer", customer, Customer.class);
        } else {
            return customerVerification;
        }
    }

    public ResponseEntity<Customer> modifyCustomer(@RequestBody Customer customer) {
        ResponseEntity customerVerification = null;
        customerVerification = verifyCustomerPeselAndBirth(customer);
        if (customerVerification.getStatusCode().value() == 200) {
            RestTemplate template = new RestTemplate();
            return template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                    .getInstances().get(0).getHomePageUrl()+"/modifycustomer", customer, Customer.class);
        } else {
            return customerVerification;
        }
    }

    public ResponseEntity searchCustomers(@RequestBody Customer customer) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl()+"/searchcustomers", customer, List.class);
    }

    public ResponseEntity deleteCustomer(@RequestBody Customer customer) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl()+ "/deletecustomer", customer, Customer.class);
    }

    public ResponseEntity getAllCustomers() {
        RestTemplate template = new RestTemplate();
        return template.getForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl()+"/getallcustomers", List.class);
    }
}
