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
    public void createTransaction(Transactions transactions) {
        em.persist(transactions);
    }

    @Transactional
    public void createPolicy(Policy newPolicy) {
        em.persist(newPolicy);
    }

    @Transactional
    public void createPolicyLine(PolicyLines newPolicyLines) {
        em.persist(newPolicyLines);
    }

    @Transactional
    public void createInsuredObject(InsuredObjects insuredObjects) {
        em.persist(insuredObjects);
    }

    @Transactional
    public ResponseEntity getTransactionId(String query) {
        Query q = em.createQuery(query);

        ArrayList<Transactions> resultArray = (ArrayList<Transactions>) q.getResultList();
        Transactions result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getVehicles(String query) {

        Query q = em.createQuery(query);

        ArrayList<Vehicles> results = (ArrayList<Vehicles>) q.getResultList();
        return ResponseEntity.ok().body(results);
    }

    @Transactional
    public Policy getPolicy(String query) {
        Query q = em.createQuery(query);

        ArrayList<Policy> resultArray = (ArrayList<Policy>) q.getResultList();
        Policy result = resultArray.get(0);
        return result;
    }

    @Transactional
    public ResponseEntity getPolicyLine(String query) {
        Query q = em.createQuery(query);

        ArrayList<PolicyLines> resultArray = (ArrayList<PolicyLines>) q.getResultList();
        PolicyLines result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getObjectRisksConfig(String query) {
        Query q = em.createQuery(query);

        ArrayList<ObjectRisksConfig> resultArray = (ArrayList<ObjectRisksConfig>) q.getResultList();
        return ResponseEntity.ok().body(resultArray);
    }

    @Transactional
    public ResponseEntity createCustomer(Customers newCustomer) {
        em.persist(newCustomer);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity deleteCustomer(Customers customerToDelete) {

        if (customerToDelete.getCustomerId() != 0) {
            /* send json to db-service */
            em.remove(customerToDelete);
        }
        return ResponseEntity.ok().body("Customer has been deleted.");
    }

    @Transactional
    public ResponseEntity modifyCustomer(String modifyQuery) {
        em.createQuery(modifyQuery).executeUpdate();
        return ResponseEntity.ok().body("Customer has been deleted.");
    }

    public List showCustomersList(String query) {
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

    public List customQuery(String query) {
        Query select = em.createQuery(query);
        List l = select.getResultList();
        return l;
    }


    @Transactional
    public int updateQuery(String query) {
        int result = em.createQuery(query).executeUpdate();
        return result;
    }

    @Transactional
    public ResponseEntity insertInsuredObject(InsuredObjects newInsuredObject) {
        em.persist(newInsuredObject);
        return ResponseEntity.ok().body(newInsuredObject);
    }

    @Transactional
    public ResponseEntity getVehicleTypes(VehicleTypesConfig vehicleTypesConfig) {
        List<VehicleTypesConfig> resultList = (List<VehicleTypesConfig>)em.createQuery("select v from VehicleTypesConfig v WHERE v.productLineType = '" + vehicleTypesConfig.getProductLineType() + "'").getResultList();
        return ResponseEntity.ok().body(resultList);
    }

    @Transactional
    public ResponseEntity createRisks(ObjectRisks risks) {
        em.persist(risks);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity updateRisk(ObjectRisks risk) {
        em.merge(risk);
        return ResponseEntity.ok().body(risk);
    }

    @Transactional
    public ResponseEntity getRisks(InsuredObjects insuredObject) {
        List<ObjectRisks> resultList = (List<ObjectRisks>)em.createQuery("select o from ObjectRisks o where o.objectId = "+insuredObject.getObjectId()).getResultList();
        return ResponseEntity.ok().body(resultList);
    }
}
