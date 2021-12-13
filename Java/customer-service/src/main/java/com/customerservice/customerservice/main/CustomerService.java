package com.customerservice.customerservice.main;

import com.customerservice.customerservice.globals.Variables;
import com.customerservice.customerservice.model.Customers;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class CustomerService {
    @Autowired
    EurekaClient eurekaClient;

    public ResponseEntity verifyCustomerPeselAndBirth(@RequestBody JSONObject jsonObject) throws ParseException {

        Customers customer = new Customers();
        String birthStr = jsonObject.get("birth_date").toString();
        Date sdf = new SimpleDateFormat("dd-MM-yyyy").parse(birthStr);
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String reversedDate = formatter.format(sdf);
        String pesel = jsonObject.get("pesel").toString();
        String substrPesel = pesel.substring(0, 6);
        StringBuilder bornAfter2000sPesel = new StringBuilder(substrPesel);
        int controlNumber = Integer.valueOf(pesel.substring(10));
        int checkSum = 0;
        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int[] peselArray = new int[11];
        char[] peselToCharArray = pesel.toCharArray();

        //checking pesel length.
        if (pesel.length() != 11) {
            customer.setName("Incorrect pesel length.");
            return ResponseEntity.ok().body(customer);
        }
        //handling pesel - date comparision for customers born after 00's
        if (pesel.startsWith("0") && pesel.charAt(2) == '2') {
            substrPesel = bornAfter2000sPesel.replace(2, 3, "0").toString();
        }
        if (pesel.startsWith("0") && pesel.charAt(2) == '3') {
            substrPesel = bornAfter2000sPesel.replace(2, 3, "1").toString();
        }
        if (!substrPesel.equals(reversedDate)) {
            customer.setName("Birth date and pesel does not match.");
            return ResponseEntity.ok().body(customer);
        }
        //control number algorithm
        for (int i = 0; i < 10; i++) {
            peselArray[i] = Character.getNumericValue(peselToCharArray[i]);
            checkSum = weights[i] * peselArray[i] + checkSum;
        }
        checkSum = 10 - (checkSum % 10) % 10;

        if (controlNumber != checkSum) {
            customer.setName("Pesel is incorrect.");
            return ResponseEntity.ok().body(customer);
        }

        customer.setName(jsonObject.get("name").toString());
        customer.setPesel(jsonObject.get("pesel").toString());
        customer.setAddress(jsonObject.get("address").toString());
        customer.setBirthDate(sdf);
        customer.setPhoneNum(BigInteger.valueOf(Long.parseLong(jsonObject.get("phone_num").toString())));

        /*send Customer to db-service*/
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName).getInstances().get(0).getHomePageUrl()+"/createcustomer",customer,String.class);
        return ResponseEntity.ok().body(customer);
    }

    public ResponseEntity deleteCustomer(@RequestBody JSONObject customerToDelete) {

        if (!customerToDelete.get("customerId").toString().isEmpty()) {
            /*send json to db-service*/
            RestTemplate template = new RestTemplate();
            ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                    .getInstances().get(0).getHomePageUrl()+"/deletecustomer",customerToDelete,String.class);
        }
        return ResponseEntity.ok().body("Customer has been deleted.");
    }

    public ResponseEntity<Customers> modifyCustomer(@RequestBody JSONObject modifiedCustomer) {
        String queryString = "";
        String updateArgs = "";

        int modifiedColumnsCounter = 0;
        Customers confirmationOfAnUpdate = new Customers();
        if (!modifiedCustomer.get("name").equals("")) {
            updateArgs += "c.name = '" + modifiedCustomer.get("name") + "'";
            modifiedColumnsCounter++;
        }
        if (!modifiedCustomer.get("pesel").equals("")) {
            updateArgs += ", c.pesel = '" + modifiedCustomer.get("pesel") + "'";
            modifiedColumnsCounter++;
        }
        if (!modifiedCustomer.get("address").equals("")) {
            updateArgs += ", c.address = ," + modifiedCustomer.get("address") + "'";
            modifiedColumnsCounter++;
        }
        if (!modifiedCustomer.get("phone_num").equals("")) {
            updateArgs += ", c.phoneNum = '" + modifiedCustomer.get("phoneNum") + "'";
                       modifiedColumnsCounter++;
        }
        if (!modifiedCustomer.get("birth_date").equals("")) {
            updateArgs += ", c.birthDate = " + modifiedCustomer.get("birthDate");
            modifiedColumnsCounter++;
        }
        queryString = "update Customers c set " + updateArgs + " where c.customerId = '" + modifiedCustomer.get("customerId") + "'";
        /*send query with args to db*/


        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl()+"/modifycustomer",queryString,String.class);
        confirmationOfAnUpdate.setName(modifiedColumnsCounter + " columns have been modified.");
        return ResponseEntity.ok().body(confirmationOfAnUpdate);
    }

    public List returnCustomersList() {
        String queryString = "select c.customerId,c.name,c.pesel,c.birthDate,c.phoneNum,c.address from Customers c";
        /*get json from db-service*/
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl()+"/showcustomerslist",queryString,List.class);

        return (List)response.getBody();
    }

    public List searchCustomerById(@RequestBody JSONObject entity) {
        /*get json from db-service*/
        String queryString = "select c.customerId,c.name,c.pesel,c.birthDate,c.phoneNum,c.address from Customers c where c.customerId = '" + entity.get("customerId") + "'";
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl()+"/showcustomerslist",queryString,List.class);

        return (List)response.getBody();
    }

    public List searchCustomerByPesel(@RequestBody JSONObject entity) {
        /*get json from db-service*/
        String queryString = "select c.customerId,c.name,c.pesel,c.birthDate,c.phoneNum,c.address from Customers c where c.pesel = '" + entity.get("pesel") + "'";
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl()+"/showcustomerslist",queryString,List.class);

        return (List)response.getBody();
    }

    public List searchCustomerByName(@RequestBody JSONObject entity) {
        String name = entity.get("name").toString().toLowerCase(Locale.ROOT);
        /*get json from db-service*/
        String queryString = "SELECT c.customerId,c.name,c.pesel,c.birthDate,c.phoneNum,c.address from Customers c WHERE LOWER(c.name) LIKE '"
                + name + "%" + "' OR LOWER(c.name) LIKE '" + "%" + name + "%" + "'";
        RestTemplate template = new RestTemplate();
        ResponseEntity response = template.postForEntity(eurekaClient.getApplication(Variables.dbName)
                .getInstances().get(0).getHomePageUrl()+"/showcustomerslist",queryString,List.class);

        return (List)response.getBody();
    }
}
