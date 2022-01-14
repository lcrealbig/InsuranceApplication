package com.insuranceapplication.dbservice;

import com.insuranceapplication.dbservice.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public ResponseEntity getTransactionId(Transactions transactions) {
        Query q = em.createQuery("select distinct t from Transactions t WHERE t.modifiedBy = '" + transactions.getModifiedBy() +
                "' AND t.timestamp = '" + transactions.getTimestamp() + "'");
        ArrayList<Transactions> resultArray = (ArrayList<Transactions>) q.getResultList();
        Transactions result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getVehicles(Vehicles vehicle) {
        Query query = null;
        if (vehicle.getBrand() == null) {
            query = em.createQuery("select distinct v.brand from Vehicles v WHERE v.vehicleType = '" + vehicle.getVehicleType() + "'");
        } else if (vehicle.getVehicleModel() == null) {
            query = em.createQuery("select distinct v.vehicleModel from Vehicles v WHERE v.brand = '" + vehicle.getBrand() + "' AND v.vehicleType = '" + vehicle.getVehicleType() + "'");
        } else if (vehicle.getGeneration() == null) {
            query = em.createQuery("select distinct v.generation from Vehicles v WHERE v.vehicleModel = '" + vehicle.getVehicleModel() +
                    "' AND v.brand = '" + vehicle.getBrand() + "'");
        } else if (vehicle.getEngineType() == null) {
            query = em.createQuery("select distinct v.engineType from Vehicles v WHERE v.generation = '" + vehicle.getGeneration() +
                    "' and v.vehicleModel = '" + vehicle.getVehicleModel() + "' AND v.brand = '" + vehicle.getBrand() + "'");
        } else if (vehicle.getEngine() == null) {
            query = em.createQuery("select distinct v.engine from Vehicles v WHERE v.engineType = '" + vehicle.getEngineType() +
                    "' and v.vehicleModel = '" + vehicle.getVehicleModel() + "' AND v.brand = '" + vehicle.getBrand() +
                    "' AND v.generation = '" + vehicle.getGeneration() + "'");
        } else {
            query = em.createQuery("select distinct v.vehicleId from Vehicles v WHERE v.engineType = '" + vehicle.getEngineType() +
                    "' and v.vehicleModel = '" + vehicle.getVehicleModel() + "' AND v.brand = '" + vehicle.getBrand() +
                    "' AND v.generation = '" + vehicle.getGeneration() + "' and v.engine = '" + vehicle.getEngine() + "'");
        }
        ArrayList<Vehicles> results = (ArrayList<Vehicles>) query.getResultList();
        return ResponseEntity.ok().body(results);
    }

    @Transactional
    public ResponseEntity getAllVehicles() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vehicles> cq = cb.createQuery(Vehicles.class);
        Root<Vehicles> rootEntry = cq.from(Vehicles.class);
        CriteriaQuery<Vehicles> all = cq.select(rootEntry);
        TypedQuery<Vehicles> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }

    @Transactional
    public ResponseEntity getVehicle(Vehicles vehicle) {
        Vehicles result = (Vehicles) em.createQuery("select v from Vehicles v WHERE v.vehicleId = " + vehicle.getVehicleId()).getSingleResult();
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity mergeVehicle(Vehicles vehicle) {
        em.merge(vehicle);
        return ResponseEntity.ok().body(vehicle);
    }


    @Transactional
    public Policy getPolicy(Policy policy) {
        Query query = em.createQuery("select p from Policy p WHERE p.transactionId = '" + policy.getTransactionId() + "'");
        ArrayList<Policy> resultArray = (ArrayList<Policy>) query.getResultList();
        Policy result = resultArray.get(0);
        return result;
    }

    @Transactional
    public ResponseEntity searchPolicy(Policy policy) {
        List<Policy> resultList = (List<Policy>) em.createQuery("select p from Policy p WHERE p.ownerId = " + policy.getOwnerId()).getResultList();
        return ResponseEntity.ok().body(resultList);
    }

    @Transactional
    public ResponseEntity getPolicyLine(PolicyLines policyLine) {
        Query query = em.createQuery("select p from PolicyLines p WHERE p.transactionId = '" + policyLine.getTransactionId() + "'");
        ArrayList<PolicyLines> resultArray = (ArrayList<PolicyLines>) query.getResultList();
        PolicyLines result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getPolicyLineTypes(PolicyLineTypesConfig policyLineTypesConfig) {
        Query query = em.createQuery("select p from PolicyLineTypesConfig p WHERE p.productId = '" + policyLineTypesConfig.getProductId() + "'");
        ArrayList<PolicyLineTypesConfig> resultArray = (ArrayList<PolicyLineTypesConfig>) query.getResultList();
        return ResponseEntity.ok().body(resultArray);
    }

    @Transactional
    public ResponseEntity searchPolicyLine(PolicyLines policyLine) {
        PolicyLines result = (PolicyLines) em.createQuery("select p from PolicyLines p WHERE p.policyId = " + policyLine.getPolicyId()).getSingleResult();
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getObjectRisksConfig(InsuredObjects insuredObjects) {
        Query q = em.createQuery("select o from ObjectRisksConfig o where o.objectType = '" + insuredObjects.getType() + "'");
        ArrayList<ObjectRisksConfig> resultArray = (ArrayList<ObjectRisksConfig>) q.getResultList();
        return ResponseEntity.ok().body(resultArray);
    }

    @Transactional
    public ResponseEntity getObjectTypes(PolicyLineTypesConfig policyLineTypesConfig) {
        Query q = em.createQuery("select o from ObjectTypesConfig o WHERE o.policyLineType = '" + policyLineTypesConfig.getPolicyLineType() + "'");
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
        } else if (customer.getPesel() != null) {
            result = em.createQuery("select c from Customers c where c.pesel like '%" + customer.getPesel() + "%'").getResultList();
        } else {
            result = em.createQuery("select c from Customers c where upper(c.name) like upper('%" + customer.getName() + "%')").getResultList();
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
        List<VehicleTypesConfig> resultList = (List<VehicleTypesConfig>) em.createQuery("select v from VehicleTypesConfig v WHERE v.productLineType = '" + vehicleTypesConfig.getPolicyLineType() + "'").getResultList();
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

    @Transactional
    public ResponseEntity getProducts() {
        List<ObjectRisks> resultList = (List<ObjectRisks>) em.createQuery("select p from ProductsConfig p").getResultList();
        return ResponseEntity.ok().body(resultList);
    }

    @Transactional
    public List getPremCalcVals() {
        List<PremiumCalcConfigValues> configValues = (List<PremiumCalcConfigValues>) em.createQuery("select pccv from PremiumCalcConfigValues pccv").getResultList();
        return configValues;
    }

    @Transactional
    public List getInsuredObjects(PolicyLines policyLine) {
        List<InsuredObjects> insuredObjects = (List<InsuredObjects>) em.createQuery("select io from InsuredObjects io where io.policyLineId = " + policyLine.getPolicyLineId()).getResultList();
        return insuredObjects;
    }

    public ResponseEntity getAllObjectFlexfields() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ObjectFlexfieldsConfig> cq = cb.createQuery(ObjectFlexfieldsConfig.class);
        Root<ObjectFlexfieldsConfig> rootEntry = cq.from(ObjectFlexfieldsConfig.class);
        CriteriaQuery<ObjectFlexfieldsConfig> all = cq.select(rootEntry);
        TypedQuery<ObjectFlexfieldsConfig> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }

    @Transactional
    public ResponseEntity mergeObjectFlexfield(ObjectFlexfieldsConfig flexfield) {
        em.merge(flexfield);
        return ResponseEntity.ok().body(flexfield);
    }

    @Transactional
    public ResponseEntity mergeObjectRiskConfig(ObjectRisksConfig risk) {
        em.merge(risk);
        return ResponseEntity.ok().body(risk);
    }

    public ResponseEntity getAllObjectRiskConfig() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ObjectRisksConfig> cq = cb.createQuery(ObjectRisksConfig.class);
        Root<ObjectRisksConfig> rootEntry = cq.from(ObjectRisksConfig.class);
        CriteriaQuery<ObjectRisksConfig> all = cq.select(rootEntry);
        TypedQuery<ObjectRisksConfig> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }

    public ResponseEntity mergeProductConfig(ProductsConfig productsConfig) {
        em.merge(productsConfig);
        return ResponseEntity.ok().body(productsConfig);
    }

    @Transactional
    public ResponseEntity mergePolicyLineTypeConfig(PolicyLineTypesConfig typesConfig) {
        em.merge(typesConfig);
        return ResponseEntity.ok().body(typesConfig);
    }

    public ResponseEntity getAllPolicyLineTypesConfig() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PolicyLineTypesConfig> cq = cb.createQuery(PolicyLineTypesConfig.class);
        Root<PolicyLineTypesConfig> rootEntry = cq.from(PolicyLineTypesConfig.class);
        CriteriaQuery<PolicyLineTypesConfig> all = cq.select(rootEntry);
        TypedQuery<PolicyLineTypesConfig> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }

    public ResponseEntity getAllPremiumHeadersConfig() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PremiumCalcConfigHeaders> cq = cb.createQuery(PremiumCalcConfigHeaders.class);
        Root<PremiumCalcConfigHeaders> rootEntry = cq.from(PremiumCalcConfigHeaders.class);
        CriteriaQuery<PremiumCalcConfigHeaders> all = cq.select(rootEntry);
        TypedQuery<PremiumCalcConfigHeaders> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }

    public ResponseEntity mergePremiumHeaderConfig(PremiumCalcConfigHeaders headers) {
        em.merge(headers);
        return ResponseEntity.ok().body(headers);
    }

    public ResponseEntity getAllPremiumValuesConfig() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PremiumCalcConfigValues> cq = cb.createQuery(PremiumCalcConfigValues.class);
        Root<PremiumCalcConfigValues> rootEntry = cq.from(PremiumCalcConfigValues.class);
        CriteriaQuery<PremiumCalcConfigValues> all = cq.select(rootEntry);
        TypedQuery<PremiumCalcConfigValues> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }

    public ResponseEntity mergePremiumValueConfig(PremiumCalcConfigValues values) {
        em.merge(values);
        return ResponseEntity.ok().body(values);
    }

    public ResponseEntity getAllCustomers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customers> cq = cb.createQuery(Customers.class);
        Root<Customers> rootEntry = cq.from(Customers.class);
        CriteriaQuery<Customers> all = cq.select(rootEntry);
        TypedQuery<Customers> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }

    @Transactional
    public void createClaim(Claims claim) {
        em.persist(claim);
    }

    @Transactional
    public void updateClaim(Claims claim) {
        em.merge(claim);
    }

    @Transactional
    public void removeClaim(Claims claim) {
        em.createQuery("delete c from Claims c where claimId = '" + claim.getClaimId() + "'");
    }

    @Transactional
    public List getClaims(InsuredObjects driver) {
        List<Claims> claims = em.createQuery("select c from Claims c where claimId = '" + driver.getObjectId() + "'").getResultList();
        return claims;
    }

    @Transactional
    public void createBill(Bills bill) {
        em.persist(bill);
    }

    @Transactional
    public void updateBill(Bills bill) {
        em.merge(bill);
    }

    @Transactional
    public void removeBill(Bills bill) {
        em.createQuery("delete c from Bills c where claimId = '" + bill.getClaimId() + "'");
    }

    @Transactional
    public List getBills(Claims claim) {
        return (List) em.createQuery("select c from Bills c where claimId = '" + claim.getClaimId() + "'");
    }

    @Transactional
    public void createVictim(Victims victim) {
        em.persist(victim);
    }

    @Transactional
    public void updateVictim(Victims victim) {
        em.merge(victim);
    }

    @Transactional
    public void removeVictim(Victims victim) {
        em.createQuery("delete c from Victims c where claimId = '" + victim.getVictimId() + "'");
    }

    @Transactional
    public List getVictims(Bills bill) {
        return (List) em.createQuery("select c from Victims c where claimId = '" + bill.getClaimId() + "'");
    }
}
