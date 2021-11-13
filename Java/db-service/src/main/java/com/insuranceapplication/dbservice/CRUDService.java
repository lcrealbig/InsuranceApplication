package com.insuranceapplication.dbservice;

import com.insuranceapplication.dbservice.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CRUDService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createTransaction(Transactions transactions) {em.persist(transactions);}

    @Transactional
    public void createPolicy(Policy newPolicy){
        em.persist(newPolicy);
    }

    @Transactional
    public void createPolicyLine(Policy_lines newPolicyLines){
        em.persist(newPolicyLines);
    }

    @Transactional
    public void createInsuredObject(InsuredObject insuredObject){
        em.persist(insuredObject);
    }

    @Transactional
    public ResponseEntity getTransactionId(String query) {
        Query q = em.createQuery(query);

        ArrayList<Transactions> resultArray = (ArrayList<Transactions>) q.getResultList();
        Transactions result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getVehicles(String query){

           Query q = em.createQuery(query);

        ArrayList<Vehicles> results = (ArrayList<Vehicles>) q.getResultList();
        return ResponseEntity.ok().body(results);
    }

    @Transactional
    public Policy getPolicy(String query){
        Query q = em.createQuery(query);

        ArrayList<Policy> resultArray = (ArrayList<Policy>) q.getResultList();
        Policy result = resultArray.get(0);
        return result;
    }

    @Transactional
    public ResponseEntity getPolicyLine(String query){
        Query q = em.createQuery(query);

        ArrayList<Policy_lines> resultArray = (ArrayList<Policy_lines>) q.getResultList();
        Policy_lines result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity createCustomer(Customers newCustomer){
        em.persist(newCustomer);
        return ResponseEntity.ok().build();
    }
    @Transactional
    public ResponseEntity deleteCustomer(Customers customerToDelete) {

        if (customerToDelete.getCustomer_id()!=0) {
            /*send json to db-service*/
            em.remove(customerToDelete);
        }
        return ResponseEntity.ok().body("Customer has been deleted.");
    }
    @Transactional
    public ResponseEntity modifyCustomer(String modifyQuery) {
        em.createQuery(modifyQuery).executeUpdate();
        return ResponseEntity.ok().body("Customer has been deleted.");
    }

    public List showCustomersList(String query){
        Query select = em.createQuery(query);
        return select.getResultList();
    }

    public ResponseEntity verifyUserLogin(@RequestBody Users user) {
        String userName = user.getName();
        String userPassword = user.getPassword();
        List<Users> dbRecords = em.createQuery("select u from Users u", Users.class).getResultList();
        for (Users u : dbRecords) {
            if (u.getName().equals(userName) && u.getPassword().equals(userPassword)) {
                return ResponseEntity.ok().body(u);
            }
        }
        Users notExist = new Users();
        notExist.setName("NOT_EXIST");
        return ResponseEntity.ok().body(notExist);
    }
}
