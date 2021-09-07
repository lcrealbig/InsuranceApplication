package com.customerservice.customerservice.main;

import com.customerservice.customerservice.model.Customers;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class CustomerService {
    @Autowired
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public ResponseEntity verifyCustomerPeselAndBirth(@RequestBody JSONObject jsonObject) throws ParseException {

        Customers customer = new Customers();
        String birthStr = jsonObject.get("birth_date").toString();
        Date sdf = new SimpleDateFormat("dd-MM-yyyy").parse(birthStr);
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String reversedDate = formatter.format(sdf);
        String peselToString = jsonObject.get("pesel").toString();
        String substrPesel = peselToString.substring(0, 6);
        int controlNumber = Integer.valueOf(peselToString.substring(10));
        int controlNumberOffAlgorithm = 0;
        int[] weightsToArray = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int[] peselToArray = new int[11];
        char[] peselToCharArray = peselToString.toCharArray();

        //checking pesel length.
        if (peselToString.length() != 11) {
            customer.setName("Incorrect pesel length.");
            return ResponseEntity.ok().body(customer);
        }
        //handling pesel - date comparision for customers born after 00's
        if (peselToString.startsWith("0")) {
            if (peselToString.charAt(2) == '2') {
                substrPesel = substrPesel.replace('2', '0');
            }
            if (peselToString.charAt(2) == '3') {
                substrPesel = substrPesel.replace('3', '1');
            }
        }
        if (!substrPesel.equals(reversedDate)) {
            customer.setName("Birth date and pesel does not match.");
            return ResponseEntity.ok().body(customer);
        }
        //control number algorithm
        for (int i = 0; i < 10; i++) {
            peselToArray[i] = Character.getNumericValue(peselToCharArray[i]);
            controlNumberOffAlgorithm = weightsToArray[i] * peselToArray[i] + controlNumberOffAlgorithm;
        }
        controlNumberOffAlgorithm = (controlNumberOffAlgorithm % 10) % 10;
        if (controlNumber != controlNumberOffAlgorithm) {
            customer.setName("Pesel is incorrect.");
            return ResponseEntity.ok().body(customer);
        }

        customer.setName(jsonObject.get("name").toString());
        customer.setPesel(jsonObject.get("pesel").toString());
        customer.setAddress(jsonObject.get("address").toString());
        customer.setBirthDate(sdf);
        customer.setPhoneNum(BigInteger.valueOf(Long.parseLong(jsonObject.get("phone_num").toString())));
        em.persist(customer);
        return ResponseEntity.ok().body(customer);
    }

    @Transactional
    public ResponseEntity deleteCustomer(@RequestBody JSONObject customerToDelete) {

        if (!customerToDelete.get("customer_id").toString().isEmpty()) {
            Customers customers = new Customers();
            customers.setCustomer_id(Integer.parseInt(customerToDelete.get("customer_id").toString()));
            customers = em.find(Customers.class, customers.getCustomer_id());
            em.remove(customers);
        }
        return ResponseEntity.ok().body("Customer has been deleted.");
    }

    @Transactional
    public ResponseEntity<Customers> modifyCustomer(@RequestBody JSONObject modifiedCustomer) {
        int query = 0;

        int modifiedColumnsCounter = 0;
        Customers confirmationOfAnUpdate = new Customers();
        if (!modifiedCustomer.get("name").equals("")) {
            query = em.createQuery("update Customers c set c.name = '" + modifiedCustomer.get("name") +
                    "' where c.customer_id = '" + modifiedCustomer.get("customer_id") + "'").executeUpdate();
            confirmationOfAnUpdate.setName("Value : name of customer, with an id: " + modifiedCustomer.get("customer_id").toString() + " have been changed.");
            modifiedColumnsCounter++;
        }
        if (!modifiedCustomer.get("pesel").equals("")) {
            query = em.createQuery("update Customers c set c.pesel = '" + modifiedCustomer.get("pesel") +
                    "' where c.customer_id = '" + modifiedCustomer.get("customer_id") + "'").executeUpdate();
            confirmationOfAnUpdate.setName("Value : pesel of customer, with an id: " + modifiedCustomer.get("customer_id").toString() + " have been changed.");
            modifiedColumnsCounter++;
        }
        if (!modifiedCustomer.get("address").equals("")) {
            query = em.createQuery("update Customers c set c.address = '" + modifiedCustomer.get("address") +
                    "' where c.customer_id = '" + modifiedCustomer.get("customer_id") + "'").executeUpdate();
            confirmationOfAnUpdate.setName("Value : address of customer, with an id: " + modifiedCustomer.get("customer_id").toString() + " have been changed.");
            modifiedColumnsCounter++;
        }
        if (!modifiedCustomer.get("phone_num").equals("")) {
            query = em.createQuery("update Customers c set c.phoneNum = '" + modifiedCustomer.get("phone_num") +
                    "' where c.customer_id = '" + modifiedCustomer.get("customer_id") + "'").executeUpdate();
            confirmationOfAnUpdate.setName("Value : phone_num of customer, with an id: " + modifiedCustomer.get("customer_id").toString() + " have been changed.");
            modifiedColumnsCounter++;
        }
        if (!modifiedCustomer.get("birth_date").equals("")) {
            query = em.createQuery("update Customers c set c.birthDate = '" + modifiedCustomer.get("birth_date") +
                    "' where c.customer_id = '" + modifiedCustomer.get("customer_id") + "'").executeUpdate();
            confirmationOfAnUpdate.setName("Value : birth_date of customer, with an id: " + modifiedCustomer.get("customer_id").toString() + " have been changed.");
            modifiedColumnsCounter++;
        }
        confirmationOfAnUpdate.setName(modifiedColumnsCounter + " columns have been modified.");
        return ResponseEntity.ok().body(confirmationOfAnUpdate);
    }

    public List returnCustomersList(@RequestBody JSONObject jsonObject) {
        Query select = em.createQuery("select c.customer_id,c.name,c.pesel,c.birthDate,c.phoneNum,c.address from Customers c");
        return select.getResultList();
    }

    public List searchCustomerById(@RequestBody JSONObject entity) {
        Query showByParams = em.createQuery("select c.customer_id,c.name,c.pesel,c.birthDate,c.phoneNum,c.address from Customers c where c.customer_id = '"
                + entity.get("customer_id") + "'");
        return showByParams.getResultList();
    }

    public List searchCustomerByPesel(@RequestBody JSONObject entity) {
        Query showByParams = em.createQuery("select c.customer_id,c.name,c.pesel,c.birthDate,c.phoneNum,c.address from Customers c where c.pesel = '"
                + entity.get("pesel") + "'");
        return showByParams.getResultList();
    }

    public List searchCustomerByName(@RequestBody JSONObject entity) {
        String name = entity.get("name").toString().toLowerCase(Locale.ROOT);
        Query showByParams = em.createQuery("SELECT c.customer_id,c.name,c.pesel,c.birthDate,c.phoneNum,c.address from Customers c WHERE LOWER(c.name) LIKE '"
                + name + "%" + "' OR LOWER(c.name) LIKE '" + "%" + name + "%" + "'");
        return showByParams.getResultList();
    }
}
