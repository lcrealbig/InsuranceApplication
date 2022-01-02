package com.customerservice.customerservice.main;

import com.customerservice.customerservice.globals.Variables;
import com.customerservice.customerservice.model.Customers;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    EurekaClient eurekaClient;

    public ResponseEntity verifyCustomerPeselAndBirth(@RequestBody Customers customer) {
        RestTemplate template = new RestTemplate();

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

        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl()+ "/createcustomer", customer, Customers.class);
    }

    public ResponseEntity deleteCustomer(@RequestBody Customers customer) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl()+ "/deletecustomer", customer, Customers.class);
    }

    public ResponseEntity<Customers> modifyCustomer(@RequestBody Customers customer) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl()+"/modifycustomer", customer, Customers.class);
    }

    public ResponseEntity searchCustomers(@RequestBody Customers customer) {
        RestTemplate template = new RestTemplate();
        return template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl()+"/searchcustomers", customer, List.class);
    }
}
