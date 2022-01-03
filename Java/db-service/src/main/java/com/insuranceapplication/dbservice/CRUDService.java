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
    public ResponseEntity searchInsuredObject(InsuredObjects insuredObject) {
        InsuredObjects result = (InsuredObjects) em.createQuery("select io from InsuredObjects io WHERE io.policyLineId = '" + insuredObject.getPolicyLineId() + "' AND io.type = '" + insuredObject.getType() + "'").getSingleResult();
        return ResponseEntity.ok().body(result);
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
    public ResponseEntity getVehicle(Vehicles vehicle) {
        Vehicles result = (Vehicles) em.createQuery("select v from Vehicles v WHERE v.vehicleId = " + vehicle.getVehicleId()).getSingleResult();
        return ResponseEntity.ok().body(result);
    }


    @Transactional
    public Policy getPolicy(String query) {
        Query q = em.createQuery(query);

        ArrayList<Policy> resultArray = (ArrayList<Policy>) q.getResultList();
        Policy result = resultArray.get(0);
        return result;
    }

    @Transactional
    public ResponseEntity searchPolicy(Policy policy) {
        List<Policy> resultList = (List<Policy>) em.createQuery("select p from Policy p WHERE p.ownerId = " + policy.getOwnerId()).getResultList();
        return ResponseEntity.ok().body(resultList);
    }

    @Transactional
    public ResponseEntity getPolicyLine(String query) {
        Query q = em.createQuery(query);

        ArrayList<PolicyLines> resultArray = (ArrayList<PolicyLines>) q.getResultList();
        PolicyLines result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity searchPolicyLine(PolicyLines policyLine) {
        PolicyLines result = (PolicyLines) em.createQuery("select p from PolicyLines p WHERE p.policyId = " + policyLine.getPolicyId()).getSingleResult();
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getObjectRisksConfig(String query) {
        Query q = em.createQuery(query);

        ArrayList<ObjectRisksConfig> resultArray = (ArrayList<ObjectRisksConfig>) q.getResultList();
        return ResponseEntity.ok().body(resultArray);
    }

    @Transactional
    public ResponseEntity createCustomer(Customers customer) {
        em.persist(customer);
        return ResponseEntity.ok().body(customer);
    }

    @Transactional
    public ResponseEntity deleteCustomer(Customers customer) {
        em.createQuery("delete from Customers c where c.customerId = " + customer.getCustomerId()).executeUpdate();
        return ResponseEntity.ok().body(customer);
    }

    @Transactional
    public ResponseEntity modifyCustomer(Customers customer) {
        em.merge(customer);
        return ResponseEntity.ok().body(customer);
    }

    @Transactional
    public ResponseEntity searchCustomers(Customers customer) {
        List<Customers> result;
        if (customer.getCustomerId() != null) {
            result = em.createQuery("select c from Customers c where c.customerId = '" + customer.getCustomerId() + "'").getResultList();
        } else if (customer.getPesel() != null){
            result = em.createQuery("select c from Customers c where c.pesel like '%" + customer.getPesel() + "%'").getResultList();
        } else {
            result = em.createQuery("select c from Customers c where c.name like upper('%" + customer.getName() + "%')").getResultList();
        }
        return ResponseEntity.ok().body(result);
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
        List<VehicleTypesConfig> resultList = (List<VehicleTypesConfig>) em.createQuery("select v from VehicleTypesConfig v WHERE v.productLineType = '" + vehicleTypesConfig.getProductLineType() + "'").getResultList();
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
    public ResponseEntity updatePolicy(Policy policy) {
        em.merge(policy);
        return ResponseEntity.ok().body(policy);
    }

    @Transactional
    public ResponseEntity updatePolicyLine(PolicyLines policyLine) {
        em.merge(policyLine);
        return ResponseEntity.ok().body(policyLine);
    }

    @Transactional
    public ResponseEntity updateInsuredVehicle(InsuredObjects insuredObject) {
        em.merge(insuredObject);
        return ResponseEntity.ok().body(insuredObject);
    }

    @Transactional
    public ResponseEntity getRisks(InsuredObjects insuredObject) {
        List<ObjectRisks> resultList = (List<ObjectRisks>) em.createQuery("select o from ObjectRisks o where o.objectId = " + insuredObject.getObjectId()).getResultList();
        return ResponseEntity.ok().body(resultList);
    }
}
