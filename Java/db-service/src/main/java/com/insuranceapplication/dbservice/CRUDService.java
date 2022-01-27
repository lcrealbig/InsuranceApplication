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
    public void createTransaction(Transaction transaction) {
        em.persist(transaction);
    }

    @Transactional
    public void createPolicy(Policy newPolicy) {
        em.persist(newPolicy);
    }

    @Transactional
    public void createPolicyLine(PolicyLine newPolicyLine) {
        em.persist(newPolicyLine);
    }

    @Transactional
    public void createInsuredObject(InsuredObject insuredObject) {
        em.persist(insuredObject);
    }

    @Transactional
    public ResponseEntity searchInsuredObject(InsuredObject insuredObject) {
        InsuredObject result = (InsuredObject) em.createQuery("SELECT io FROM InsuredObject io WHERE io.policyLineId = '" + insuredObject.getPolicyLineId() + "' AND io.type = '" + insuredObject.getType() + "'").getSingleResult();
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getTransactionId(Transaction transaction) {
        Query q = em.createQuery("SELECT DISTINCT t FROM Transaction t WHERE t.modifiedBy = '" + transaction.getModifiedBy() +
                "' AND t.timestamp = '" + transaction.getTimestamp() + "'");
        ArrayList<Transaction> resultArray = (ArrayList<Transaction>) q.getResultList();
        Transaction result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getVehicles(Vehicle vehicle) {
        Query query = null;
        if (vehicle.getBrand() == null) {
            query = em.createQuery("SELECT DISTINCT v.brand FROM Vehicle v WHERE v.vehicleType = '" + vehicle.getVehicleType() + "'");
        } else if (vehicle.getVehicleModel() == null) {
            query = em.createQuery("SELECT DISTINCT v.vehicleModel FROM Vehicle v WHERE v.brand = '" + vehicle.getBrand() + "' AND v.vehicleType = '" + vehicle.getVehicleType() + "'");
        } else if (vehicle.getGeneration() == null) {
            query = em.createQuery("SELECT DISTINCT v.generation FROM Vehicle v WHERE v.vehicleModel = '" + vehicle.getVehicleModel() +
                    "' AND v.brand = '" + vehicle.getBrand() + "'");
        } else if (vehicle.getEngineType() == null) {
            query = em.createQuery("SELECT DISTINCT v.engineType FROM Vehicle v WHERE v.generation = '" + vehicle.getGeneration() +
                    "' AND v.vehicleModel = '" + vehicle.getVehicleModel() + "' AND v.brand = '" + vehicle.getBrand() + "'");
        } else if (vehicle.getEngine() == null) {
            query = em.createQuery("SELECT DISTINCT v.engine FROM Vehicle v WHERE v.engineType = '" + vehicle.getEngineType() +
                    "' AND v.vehicleModel = '" + vehicle.getVehicleModel() + "' AND v.brand = '" + vehicle.getBrand() +
                    "' AND v.generation = '" + vehicle.getGeneration() + "'");
        } else {
            query = em.createQuery("SELECT DISTINCT v.vehicleId FROM Vehicle v WHERE v.engineType = '" + vehicle.getEngineType() +
                    "' AND v.vehicleModel = '" + vehicle.getVehicleModel() + "' AND v.brand = '" + vehicle.getBrand() +
                    "' AND v.generation = '" + vehicle.getGeneration() + "' AND v.engine = '" + vehicle.getEngine() + "'");
        }
        ArrayList<Vehicle> results = (ArrayList<Vehicle>) query.getResultList();
        return ResponseEntity.ok().body(results);
    }

    @Transactional
    public ResponseEntity getAllVehicles() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vehicle> cq = cb.createQuery(Vehicle.class);
        Root<Vehicle> rootEntry = cq.from(Vehicle.class);
        CriteriaQuery<Vehicle> all = cq.select(rootEntry);
        TypedQuery<Vehicle> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }

    @Transactional
    public ResponseEntity getVehicle(Vehicle vehicle) {
        Vehicle result = (Vehicle) em.createQuery("SELECT v FROM Vehicle v WHERE v.vehicleId = " + vehicle.getId()).getSingleResult();
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity mergeVehicle(Vehicle vehicle) {
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
    public ResponseEntity getPolicyLine(PolicyLine policyLine) {
        Query query = em.createQuery("SELECT p FROM PolicyLine p WHERE p.transactionId = '" + policyLine.getTransactionId() + "'");
        ArrayList<PolicyLine> resultArray = (ArrayList<PolicyLine>) query.getResultList();
        PolicyLine result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getPolicyLineTypes(PolicyLineTypeConfig policyLineTypeConfig) {
        Query query = em.createQuery("SELECT p FROM PolicyLineTypeConfig p WHERE p.productId = '" + policyLineTypeConfig.getProductId() +
                "' and p.version ='" + policyLineTypeConfig.getVersion() + "'");
        ArrayList<PolicyLineTypeConfig> resultArray = (ArrayList<PolicyLineTypeConfig>) query.getResultList();
        return ResponseEntity.ok().body(resultArray);
    }

    @Transactional
    public ResponseEntity searchPolicyLine(PolicyLine policyLine) {
        PolicyLine result = (PolicyLine) em.createQuery("SELECT p FROM PolicyLine p WHERE p.policyId = " + policyLine.getPolicyId()).getSingleResult();
        return ResponseEntity.ok().body(result);
    }

    @Transactional
    public ResponseEntity getObjectRisksConfig(InsuredObject insuredObject) {
        Query q = em.createQuery("SELECT o FROM ObjectRiskConfig o WHERE o.objectType = '" + insuredObject.getType() + "'");
        ArrayList<ObjectRiskConfig> resultArray = (ArrayList<ObjectRiskConfig>) q.getResultList();
        return ResponseEntity.ok().body(resultArray);
    }

    @Transactional
    public ResponseEntity getObjectTypes(PolicyLineTypeConfig policyLineTypeConfig) {
        Query q = em.createQuery("SELECT o FROM ObjectTypeConfig o WHERE o.policyLineType = '" + policyLineTypeConfig.getPolicyLineType() + "' AND o.version = '" + policyLineTypeConfig.getVersion() + "'");
        ArrayList<ObjectRiskConfig> resultArray = (ArrayList<ObjectRiskConfig>) q.getResultList();
        return ResponseEntity.ok().body(resultArray);
    }

    @Transactional
    public ResponseEntity createCustomer(Customer customer) {
        em.persist(customer);
        return ResponseEntity.ok().body(customer);
    }

    @Transactional
    public ResponseEntity deleteCustomer(Customer customer) {
        em.createQuery("DELETE FROM Customer c WHERE c.customerId = " + customer.getId()).executeUpdate();
        return ResponseEntity.ok().body(customer);
    }


    @Transactional
    public ResponseEntity modifyCustomer(Customer customer) {
        em.merge(customer);
        return ResponseEntity.ok().body(customer);
    }

    @Transactional
    public ResponseEntity searchCustomers(Customer customer) {
        List<Customer> result;
        if (customer.getId() != null) {
            result = em.createQuery("SELECT c FROM Customer c WHERE c.customerId = '" + customer.getId() + "'").getResultList();
        } else if (customer.getPesel() != null) {
            result = em.createQuery("SELECT c FROM Customer c WHERE c.pesel like '%" + customer.getPesel() + "%'").getResultList();
        } else {
            result = em.createQuery("SELECT c FROM Customer c WHERE upper(c.name) like upper('%" + customer.getName() + "%')").getResultList();
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
    public ResponseEntity insertInsuredObject(InsuredObject newInsuredObject) {
        em.persist(newInsuredObject);
        return ResponseEntity.ok().body(newInsuredObject);
    }

    @Transactional
    public ResponseEntity getVehicleTypes(VehicleTypeConfig vehicleTypeConfig) {
        List<VehicleTypeConfig> resultList = (List<VehicleTypeConfig>) em.createQuery("SELECT v FROM VehicleTypeConfig v WHERE v.policyLineType = '" + vehicleTypeConfig.getPolicyLineType() + "' AND v.version ='" + vehicleTypeConfig.getVersion() + "'").getResultList();
        return ResponseEntity.ok().body(resultList);
    }

    @Transactional
    public ResponseEntity createRisks(ObjectRisk risks) {
        em.persist(risks);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity updateRisk(ObjectRisk risk) {
        em.merge(risk);
        return ResponseEntity.ok().body(risk);
    }

    @Transactional
    public ResponseEntity updatePolicy(Policy policy) {
        em.merge(policy);
        return ResponseEntity.ok().body(policy);
    }

    @Transactional
    public ResponseEntity updatePolicyLine(PolicyLine policyLine) {
        em.merge(policyLine);
        return ResponseEntity.ok().body(policyLine);
    }

    @Transactional
    public ResponseEntity updateInsuredVehicle(InsuredObject insuredObject) {
        em.merge(insuredObject);
        return ResponseEntity.ok().body(insuredObject);
    }

    @Transactional
    public ResponseEntity getRisks(InsuredObject insuredObject) {
        List<ObjectRisk> resultList = (List<ObjectRisk>) em.createQuery("SELECT o FROM ObjectRisk o WHERE o.objectId = " + insuredObject.getId()).getResultList();
        return ResponseEntity.ok().body(resultList);
    }

    @Transactional
    public ResponseEntity getProducts(ProductConfig productConfig) {
        List<ObjectRisk> resultList = (List<ObjectRisk>) em.createQuery("SELECT p FROM ProductConfig p WHERE p.startDate<=to_date('"+ productConfig.getStartDate() + "','yyyy-MM-dd') and coalesce(p.endDate,current_date+10000)>to_date('"+ productConfig.getStartDate() + "','yyyy-MM-dd')").getResultList();
        return ResponseEntity.ok().body(resultList);
    }

    @Transactional
    public List getPremCalcVals(Policy policy) {
        List<PremiumCalcConfigValue> configValues = (List<PremiumCalcConfigValue>) em.createQuery("SELECT pccv FROM PremiumCalcConfigValue pccv WHERE pccv.version ='" + policy.getVersion() + "'").getResultList();
        return configValues;
    }

    @Transactional
    public List getInsuredObjects(PolicyLine policyLine) {
        List<InsuredObject> insuredObjects = (List<InsuredObject>) em.createQuery("SELECT io FROM InsuredObject io WHERE io.policyLineId = " + policyLine.getId()).getResultList();
        return insuredObjects;
    }

    public ResponseEntity getAllObjectFlexfields() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ObjectFlexfieldConfig> cq = cb.createQuery(ObjectFlexfieldConfig.class);
        Root<ObjectFlexfieldConfig> rootEntry = cq.from(ObjectFlexfieldConfig.class);
        CriteriaQuery<ObjectFlexfieldConfig> all = cq.select(rootEntry);
        TypedQuery<ObjectFlexfieldConfig> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }


    @Transactional
    public ResponseEntity mergeObjectFlexfield(ObjectFlexfieldConfig flexfield) {
        em.merge(flexfield);
        return ResponseEntity.ok().body(flexfield);
    }

    @Transactional
    public ResponseEntity mergeObjectRiskConfig(ObjectRiskConfig risk) {
        em.merge(risk);
        return ResponseEntity.ok().body(risk);
    }

    public ResponseEntity getAllObjectRiskConfig() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ObjectRiskConfig> cq = cb.createQuery(ObjectRiskConfig.class);
        Root<ObjectRiskConfig> rootEntry = cq.from(ObjectRiskConfig.class);
        CriteriaQuery<ObjectRiskConfig> all = cq.select(rootEntry);
        TypedQuery<ObjectRiskConfig> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }

    public ResponseEntity mergeProductConfig(ProductConfig productConfig) {
        em.merge(productConfig);
        return ResponseEntity.ok().body(productConfig);
    }

    @Transactional
    public ResponseEntity mergePolicyLineTypeConfig(PolicyLineTypeConfig typesConfig) {
        em.merge(typesConfig);
        return ResponseEntity.ok().body(typesConfig);
    }

    public ResponseEntity getAllPolicyLineTypesConfig() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PolicyLineTypeConfig> cq = cb.createQuery(PolicyLineTypeConfig.class);
        Root<PolicyLineTypeConfig> rootEntry = cq.from(PolicyLineTypeConfig.class);
        CriteriaQuery<PolicyLineTypeConfig> all = cq.select(rootEntry);
        TypedQuery<PolicyLineTypeConfig> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }

    public ResponseEntity getAllPremiumHeadersConfig() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PremiumCalcConfigHeader> cq = cb.createQuery(PremiumCalcConfigHeader.class);
        Root<PremiumCalcConfigHeader> rootEntry = cq.from(PremiumCalcConfigHeader.class);
        CriteriaQuery<PremiumCalcConfigHeader> all = cq.select(rootEntry);
        TypedQuery<PremiumCalcConfigHeader> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }

    public ResponseEntity mergePremiumHeaderConfig(PremiumCalcConfigHeader headers) {
        em.merge(headers);
        return ResponseEntity.ok().body(headers);
    }

    public ResponseEntity getAllPremiumValuesConfig() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PremiumCalcConfigValue> cq = cb.createQuery(PremiumCalcConfigValue.class);
        Root<PremiumCalcConfigValue> rootEntry = cq.from(PremiumCalcConfigValue.class);
        CriteriaQuery<PremiumCalcConfigValue> all = cq.select(rootEntry);
        TypedQuery<PremiumCalcConfigValue> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }

    public ResponseEntity mergePremiumValueConfig(PremiumCalcConfigValue values) {
        em.merge(values);
        return ResponseEntity.ok().body(values);
    }

    public ResponseEntity getAllCustomers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> rootEntry = cq.from(Customer.class);
        CriteriaQuery<Customer> all = cq.select(rootEntry);
        TypedQuery<Customer> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }

    @Transactional
    public ResponseEntity createClaim(Claim claim) {
        em.persist(claim);
        return ResponseEntity.ok().body(claim);
    }

    @Transactional
    public ResponseEntity updateClaim(Claim claim) {
        em.merge(claim);
        return ResponseEntity.ok().body(claim);
    }

    @Transactional
    public ResponseEntity deleteClaim(Claim claim) {
        em.createQuery("DELETE c FROM Claim c WHERE claimId = '" + claim.getClaimId() + "'");
        return ResponseEntity.ok().body(claim);
    }

    @Transactional
    public ResponseEntity getClaims(InsuredObject driver) {
        List<Claim> claims = em.createQuery("SELECT c FROM Claim c WHERE claimId = '" + driver.getId() + "'").getResultList();
        return ResponseEntity.ok().body(claims);
    }

    @Transactional
    public ResponseEntity createBill(Bill bill) {
        em.persist(bill);
        return ResponseEntity.ok().body(bill);
    }

    @Transactional
    public ResponseEntity updateBill(Bill bill) {
        em.merge(bill);
        return ResponseEntity.ok().body(bill);
    }

    @Transactional
    public ResponseEntity deleteBill(Bill bill) {
        em.createQuery("DELETE b FROM Bill b WHERE b.claimId = '" + bill.getClaimId() + "'");
        return ResponseEntity.ok().body(bill);
    }

    @Transactional
    public ResponseEntity getBills(Claim claim) {
        List<Bill> bills = em.createQuery("SELECT b FROM Bill b WHERE b.claimId = '" + claim.getClaimId() + "'").getResultList();
        return ResponseEntity.ok().body(bills);
    }

    @Transactional
    public ResponseEntity createVictim(Victim victim) {
        em.persist(victim);
        return ResponseEntity.ok().body(victim);
    }

    @Transactional
    public ResponseEntity updateVictim(Victim victim) {
        em.merge(victim);
        return ResponseEntity.ok().body(victim);
    }

    @Transactional
    public ResponseEntity deleteVictim(Victim victim) {
        em.createQuery("DELETE v FROM Victim v WHERE v.claimId = '" + victim.getId() + "'");
        return ResponseEntity.ok().body(victim);
    }

    @Transactional
    public ResponseEntity getVictims(Bill bill) {
        List<Victim> victims = em.createQuery("SELECT v FROM Victim v WHERE v.claimId = '" + bill.getClaimId() + "'").getResultList();
        return ResponseEntity.ok().body(victims);
    }

    public ResponseEntity getAllProductConfig() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductConfig> cq = cb.createQuery(ProductConfig.class);
        Root<ProductConfig> rootEntry = cq.from(ProductConfig.class);
        CriteriaQuery<ProductConfig> all = cq.select(rootEntry);
        TypedQuery<ProductConfig> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }
}
