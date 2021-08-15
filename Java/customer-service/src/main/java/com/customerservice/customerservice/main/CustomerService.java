package com.customerservice.customerservice.main;

import com.customerservice.customerservice.model.Customers;
import com.customerservice.customerservice.utils.Utils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigInteger;

@Service
public class CustomerService {
    //TO DO.
    @Autowired
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public ResponseEntity verifyCustomerPeselAndBirth(@RequestBody JSONObject jsonObject) {

        String birthDate = jsonObject.get("birth_date").toString().replace("-", "");
        StringBuilder birthDateToReverse = new StringBuilder(birthDate).delete(4, 6);
        String reversedDate = Utils.convertADate(birthDateToReverse);
        BigInteger pesel = BigInteger.valueOf(Long.parseLong(jsonObject.get("pesel").toString()));
        String pesel2String = String.valueOf(pesel);
        Customers customer = new Customers();
        int peselLength = pesel2String.length();
        if (peselLength != 11) {
            customer.setName("Incorrect pesel length.");
            return ResponseEntity.ok().body(customer);
        }
        String subPesel = pesel2String.substring(0, 6);
        if (!subPesel.equals(reversedDate)) {
            customer.setName("Birth date and pesel does not match.");
            return ResponseEntity.ok().body(customer);
        }
        for (int i = 0; i <= 10; i++) {
            int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
            char[] trimPesel = pesel2String.substring(0, 10).toCharArray();
            int controlNumberOffPesel = Integer.parseInt(pesel2String.substring(10));
            int controlNumberOffAlgorithm = 0;
            controlNumberOffAlgorithm = controlNumberOffAlgorithm + weights[i] * trimPesel[i];
            controlNumberOffAlgorithm = controlNumberOffAlgorithm % 10;
            controlNumberOffAlgorithm = 10 - controlNumberOffAlgorithm;

            if (controlNumberOffAlgorithm != controlNumberOffPesel) {
                customer.setName("Pesel is incorrect.");
                return ResponseEntity.ok().body(customer);
            }
        }
        customer.setName(jsonObject.get("name").toString());
        customer.setPesel(pesel);
        customer.setAddress(jsonObject.get("address").toString());
        customer.setBirthDate(jsonObject.get("birth_date").toString());
        customer.setPhoneNum(BigInteger.valueOf(Long.parseLong(jsonObject.get("phone_num").toString())));
        em.persist(customer);
        return ResponseEntity.ok().body(customer);
    }

    @Transactional
    public void modifyCustomer() {
    }

    @Transactional
    public void dropCustomer() {

    }
}
