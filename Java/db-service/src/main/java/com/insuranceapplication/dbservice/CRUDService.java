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
        InsuredObjects result = (InsuredObjects) em.createQuery("SELECT io FROM InsuredObjects io WHERE io.policyLineId = '" + insuredObject.getPolicyLineId() + "' AND io.type = '" + insuredObject.getType() + "'").getSingleResult();
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getTransactionId(Transactions transactions) {
        Query q = em.createQuery("SELECT DISTINCT t FROM Transactions t WHERE t.modifiedBy = '" + transactions.getModifiedBy() +
                "' AND t.timestamp = '" + transactions.getTimestamp() + "'");
        ArrayList<Transactions> resultArray = (ArrayList<Transactions>) q.getResultList();
        Transactions result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getVehicles(Vehicles vehicle) {
        Query query = null;
        if (vehicle.getBrand() == null) {
            query = em.createQuery("SELECT DISTINCT v.brand FROM Vehicles v WHERE v.vehicleType = '" + vehicle.getVehicleType() + "'");
        } else if (vehicle.getVehicleModel() == null) {
            query = em.createQuery("SELECT DISTINCT v.vehicleModel FROM Vehicles v WHERE v.brand = '" + vehicle.getBrand() + "' AND v.vehicleType = '" + vehicle.getVehicleType() + "'");
        } else if (vehicle.getGeneration() == null) {
            query = em.createQuery("SELECT DISTINCT v.generation FROM Vehicles v WHERE v.vehicleModel = '" + vehicle.getVehicleModel() +
                    "' AND v.brand = '" + vehicle.getBrand() + "'");
        } else if (vehicle.getEngineType() == null) {
            query = em.createQuery("SELECT DISTINCT v.engineType FROM Vehicles v WHERE v.generation = '" + vehicle.getGeneration() +
                    "' AND v.vehicleModel = '" + vehicle.getVehicleModel() + "' AND v.brand = '" + vehicle.getBrand() + "'");
        } else if (vehicle.getEngine() == null) {
            query = em.createQuery("SELECT DISTINCT v.engine FROM Vehicles v WHERE v.engineType = '" + vehicle.getEngineType() +
                    "' AND v.vehicleModel = '" + vehicle.getVehicleModel() + "' AND v.brand = '" + vehicle.getBrand() +
                    "' AND v.generation = '" + vehicle.getGeneration() + "'");
        } else {
            query = em.createQuery("SELECT DISTINCT v.vehicleId FROM Vehicles v WHERE v.engineType = '" + vehicle.getEngineType() +
                    "' AND v.vehicleModel = '" + vehicle.getVehicleModel() + "' AND v.brand = '" + vehicle.getBrand() +
                    "' AND v.generation = '" + vehicle.getGeneration() + "' AND v.engine = '" + vehicle.getEngine() + "'");
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
        Vehicles result = (Vehicles) em.createQuery("SELECT v FROM Vehicles v WHERE v.vehicleId = " + vehicle.getVehicleId()).getSingleResult();
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity mergeVehicle(Vehicles vehicle) {
        em.merge(vehicle);
        return ResponseEntity.ok().body(vehicle);
    }


    @Transactional
    public Policy getPolicy(Policy policy) {
        Query query = em.createQuery("SELECT p FROM Policy p WHERE p.transactionId = '" + policy.getTransactionId() + "' and p.version = '" + policy.getVersion() + "'");
        ArrayList<Policy> resultArray = (ArrayList<Policy>) query.getResultList();
        Policy result = resultArray.get(0);
        return result;
    }

    @Transactional
    public ResponseEntity searchPolicy(Policy policy) {
        List<Policy> resultList = (List<Policy>) em.createQuery("SELECT p FROM Policy p WHERE p.ownerId = " + policy.getOwnerId()).getResultList();
        return ResponseEntity.ok().body(resultList);
    }

    @Transactional
    public ResponseEntity getPolicyLine(PolicyLines policyLine) {
        Query query = em.createQuery("SELECT p FROM PolicyLines p WHERE p.transactionId = '" + policyLine.getTransactionId() + "'");
        ArrayList<PolicyLines> resultArray = (ArrayList<PolicyLines>) query.getResultList();
        PolicyLines result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getPolicyLineTypes(PolicyLineTypesConfig policyLineTypesConfig) {
        Query query = em.createQuery("SELECT p FROM PolicyLineTypesConfig p WHERE p.productId = '" + policyLineTypesConfig.getProductId() +
                "' and p.version ='" + policyLineTypesConfig.getVersion() + "'");
        ArrayList<PolicyLineTypesConfig> resultArray = (ArrayList<PolicyLineTypesConfig>) query.getResultList();
        return ResponseEntity.ok().body(resultArray);
    }

    @Transactional
    public ResponseEntity searchPolicyLine(PolicyLines policyLine) {
        PolicyLines result = (PolicyLines) em.createQuery("SELECT p FROM PolicyLines p WHERE p.policyId = " + policyLine.getPolicyId()).getSingleResult();
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getObjectRisksConfig(InsuredObjects insuredObjects) {
        Query q = em.createQuery("SELECT o FROM ObjectRisksConfig o WHERE o.objectType = '" + insuredObjects.getType() + "'");
        ArrayList<ObjectRisksConfig> resultArray = (ArrayList<ObjectRisksConfig>) q.getResultList();
        return ResponseEntity.ok().body(resultArray);
    }

    @Transactional
    public ResponseEntity getObjectTypes(PolicyLineTypesConfig policyLineTypesConfig) {
        Query q = em.createQuery("SELECT o FROM ObjectTypesConfig o WHERE o.policyLineType = '" + policyLineTypesConfig.getPolicyLineType() + "' AND o.version = '" + policyLineTypesConfig.getVersion() + "'");
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
        em.createQuery("DELETE FROM Customers c WHERE c.customerId = " + customer.getCustomerId()).executeUpdate();
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
            result = em.createQuery("SELECT c FROM Customers c WHERE c.customerId = '" + customer.getCustomerId() + "'").getResultList();
        } else if (customer.getPesel() != null) {
            result = em.createQuery("SELECT c FROM Customers c WHERE c.pesel like '%" + customer.getPesel() + "%'").getResultList();
        } else {
            result = em.createQuery("SELECT c FROM Customers c WHERE upper(c.name) like upper('%" + customer.getName() + "%')").getResultList();
        }
        return ResponseEntity.ok().body(result);
    }

    public ResponseEntity verifyUserLogin(@RequestBody Users user) {
        String userName = user.getName();
        String userPassword = user.getPassword();
        List<Users> dbRecords = em.createQuery("SELECT u FROM Users u", Users.class).getResultList();
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
        List<VehicleTypesConfig> resultList = (List<VehicleTypesConfig>) em.createQuery("SELECT v FROM VehicleTypesConfig v WHERE v.policyLineType = '" + vehicleTypesConfig.getPolicyLineType() + "' AND v.version ='" + vehicleTypesConfig.getVersion() + "'").getResultList();
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
        List<ObjectRisks> resultList = (List<ObjectRisks>) em.createQuery("SELECT o FROM ObjectRisks o WHERE o.objectId = " + insuredObject.getObjectId()).getResultList();
        return ResponseEntity.ok().body(resultList);
    }

    @Transactional
    public ResponseEntity getProducts(ProductsConfig productsConfig) {
        List<ObjectRisks> resultList = (List<ObjectRisks>) em.createQuery("SELECT p FROM ProductsConfig p WHERE p.startDate<=to_date('"+productsConfig.getStartDate() + "','yyyy-MM-dd') and coalesce(p.endDate,current_date+10000)>to_date('"+productsConfig.getStartDate() + "','yyyy-MM-dd')").getResultList();
        return ResponseEntity.ok().body(resultList);
    }

    @Transactional
    public List getPremCalcVals(PolicyLines policyLine) {
        List<PremiumCalcConfigValues> configValues = (List<PremiumCalcConfigValues>) em.createQuery("SELECT pccv FROM PremiumCalcConfigValues pccv WHERE pccv.version ='" + policyLine.getVersion() + "'").getResultList();
        return configValues;
    }

    @Transactional
    public List getInsuredObjects(PolicyLines policyLine) {
        List<InsuredObjects> insuredObjects = (List<InsuredObjects>) em.createQuery("SELECT io FROM InsuredObjects io WHERE io.policyLineId = " + policyLine.getPolicyLineId()).getResultList();
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
    public ResponseEntity createClaim(Claims claim) {
        em.persist(claim);
        return ResponseEntity.ok().body(claim);
    }

    @Transactional
    public ResponseEntity updateClaim(Claims claim) {
        em.merge(claim);
        return ResponseEntity.ok().body(claim);
    }

    @Transactional
    public ResponseEntity deleteClaim(Claims claim) {
        em.createQuery("DELETE c FROM Claims c WHERE claimId = '" + claim.getClaimId() + "'");
        return ResponseEntity.ok().body(claim);
    }

    @Transactional
    public ResponseEntity getClaims(InsuredObjects driver) {
        List<Claims> claims = em.createQuery("SELECT c FROM Claims c WHERE claimId = '" + driver.getObjectId() + "'").getResultList();
        return ResponseEntity.ok().body(claims);
    }

    @Transactional
    public ResponseEntity createBill(Bills bill) {
        em.persist(bill);
        return ResponseEntity.ok().body(bill);
    }

    @Transactional
    public ResponseEntity updateBill(Bills bill) {
        em.merge(bill);
        return ResponseEntity.ok().body(bill);
    }

    @Transactional
    public ResponseEntity deleteBill(Bills bill) {
        em.createQuery("DELETE b FROM Bills b WHERE b.claimId = '" + bill.getClaimId() + "'");
        return ResponseEntity.ok().body(bill);
    }

    @Transactional
    public ResponseEntity getBills(Claims claim) {
        List<Bills> bills = em.createQuery("SELECT b FROM Bills b WHERE b.claimId = '" + claim.getClaimId() + "'").getResultList();
        return ResponseEntity.ok().body(bills);
    }

    @Transactional
    public ResponseEntity createVictim(Victims victim) {
        em.persist(victim);
        return ResponseEntity.ok().body(victim);
    }

    @Transactional
    public ResponseEntity updateVictim(Victims victim) {
        em.merge(victim);
        return ResponseEntity.ok().body(victim);
    }

    @Transactional
    public ResponseEntity deleteVictim(Victims victim) {
        em.createQuery("DELETE v FROM Victims v WHERE v.claimId = '" + victim.getVictimId() + "'");
        return ResponseEntity.ok().body(victim);
    }

    @Transactional
    public ResponseEntity getVictims(Bills bill) {
        List<Victims> victims = em.createQuery("SELECT v FROM Victims v WHERE v.claimId = '" + bill.getClaimId() + "'").getResultList();
        return ResponseEntity.ok().body(victims);
    }

    public ResponseEntity getAllProductConfig() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductsConfig> cq = cb.createQuery(ProductsConfig.class);
        Root<ProductsConfig> rootEntry = cq.from(ProductsConfig.class);
        CriteriaQuery<ProductsConfig> all = cq.select(rootEntry);
        TypedQuery<ProductsConfig> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }
}
