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

        String birthStr = jsonObject.get("birth_date").toString();
        Date sdf = new SimpleDateFormat("dd-MM-yyyy").parse(birthStr);
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String reversedDate = formatter.format(sdf);
        BigInteger pesel = BigInteger.valueOf(Long.parseLong(jsonObject.get("pesel").toString()));
        String pesel2String = String.valueOf(pesel);
        String subPesel = pesel2String.substring(0, 6);
        Customers customer = new Customers();

        if (pesel2String.length() != 11) {
            customer.setName("Incorrect pesel length.");
            return ResponseEntity.ok().body(customer);
        }
        if (!subPesel.equals(reversedDate)) {
            customer.setName("Birth date and pesel does not match.");
            return ResponseEntity.ok().body(customer);
        }
        for (int i = 0; i < 1; i++) {
            int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
            char[] trimPesel = pesel2String.substring(0, 10).toCharArray();
            int peselControlNumber = Integer.parseInt(pesel2String.substring(10));
            int sumOfMultiplying = 0;
            int controlNumberAlgorithm = 10 - (weights[i] * trimPesel[i] + sumOfMultiplying) % 10;

            if (peselControlNumber != controlNumberAlgorithm) {
                customer.setName("Pesel is incorrect.");
                return ResponseEntity.ok().body(customer);
            }
        }
        customer.setName(jsonObject.get("name").toString());
        customer.setPesel(pesel);
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
        return null;
    }

    @Transactional
    public ResponseEntity modifyCustomer(@RequestBody JSONObject modifiedCustomer) {
        int query = 0;
        if (modifiedCustomer.containsKey("name")) {
            query = em.createQuery("update Customers c set c.name = '" + modifiedCustomer.get("name") +
                    "' where c.customer_id = '" + modifiedCustomer.get("customer_id") + "'").executeUpdate();
        }
        if (modifiedCustomer.containsKey("pesel")) {
            query = em.createQuery("update Customers c set c.pesel = '" + modifiedCustomer.get("pesel") +
                    "' where c.customer_id = '" + modifiedCustomer.get("customer_id") + "'").executeUpdate();
        }
        if (modifiedCustomer.containsKey("address")) {
            query = em.createQuery("update Customers c set c.address = '" + modifiedCustomer.get("address") +
                    "' where c.customer_id = '" + modifiedCustomer.get("customer_id") + "'").executeUpdate();
        }
        if (modifiedCustomer.containsKey("phone_num")) {
            query = em.createQuery("update Customers c set c.phoneNum = '" + modifiedCustomer.get("phone_num") +
                    "' where c.customer_id = '" + modifiedCustomer.get("customer_id") + "'").executeUpdate();
        }
        if (modifiedCustomer.containsKey("birth_date")) {
            query = em.createQuery("update Customers c set c.birthDate = '" + modifiedCustomer.get("birth_date") +
                    "' where c.customer_id = '" + modifiedCustomer.get("customer_id") + "'").executeUpdate();
        }
        Customers emptyModification = new Customers();
        emptyModification.setName("No modification was made.");
        return ResponseEntity.ok().body(emptyModification);
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
