package com.insuranceapplication.dbservice;

import com.insuranceapplication.dbservice.interfaces.UserService;
import com.insuranceapplication.dbservice.models.*;
import com.insuranceapplication.dbservice.models.authentication.Role;
import com.insuranceapplication.dbservice.models.authentication.User;
import com.insuranceapplication.dbservice.repository.RoleRepository;
import com.insuranceapplication.dbservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@Transactional
public class CRUDService implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PersistenceContext
    private EntityManager em;

    public CRUDService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public void createTransaction(Transactions transactions) {
        em.persist(transactions);
    }


    public void createPolicy(Policy newPolicy) {
        em.persist(newPolicy);
    }


    public void createPolicyLine(PolicyLines newPolicyLines) {
        em.persist(newPolicyLines);
    }


    public void createInsuredObject(InsuredObjects insuredObjects) {
        em.persist(insuredObjects);
    }


    public ResponseEntity searchInsuredObject(InsuredObjects insuredObject) {
        InsuredObjects result = (InsuredObjects) em.createQuery("select io from InsuredObjects io WHERE io.policyLineId = '" + insuredObject.getPolicyLineId() + "' AND io.type = '" + insuredObject.getType() + "'").getSingleResult();
        return ResponseEntity.ok().body(result);
    }


    public ResponseEntity getTransactionId(String query) {
        Query q = em.createQuery(query);

        ArrayList<Transactions> resultArray = (ArrayList<Transactions>) q.getResultList();
        Transactions result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }


    public ResponseEntity getVehicles(String query) {

        Query q = em.createQuery(query);

        ArrayList<Vehicles> results = (ArrayList<Vehicles>) q.getResultList();
        return ResponseEntity.ok().body(results);
    }


    public ResponseEntity getAllVehicles() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vehicles> cq = cb.createQuery(Vehicles.class);
        Root<Vehicles> rootEntry = cq.from(Vehicles.class);
        CriteriaQuery<Vehicles> all = cq.select(rootEntry);
        TypedQuery<Vehicles> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }


    public ResponseEntity getVehicle(Vehicles vehicle) {
        Vehicles result = (Vehicles) em.createQuery("select v from Vehicles v WHERE v.vehicleId = " + vehicle.getVehicleId()).getSingleResult();
        return ResponseEntity.ok().body(result);
    }


    public ResponseEntity mergeVehicle(Vehicles vehicle) {
        em.merge(vehicle);
        return ResponseEntity.ok().body(vehicle);
    }


    public Policy getPolicy(String query) {
        Query q = em.createQuery(query);

        ArrayList<Policy> resultArray = (ArrayList<Policy>) q.getResultList();
        Policy result = resultArray.get(0);
        return result;
    }


    public ResponseEntity searchPolicy(Policy policy) {
        List<Policy> resultList = (List<Policy>) em.createQuery("select p from Policy p WHERE p.ownerId = " + policy.getOwnerId()).getResultList();
        return ResponseEntity.ok().body(resultList);
    }


    public ResponseEntity getPolicyLine(String query) {
        Query q = em.createQuery(query);

        ArrayList<PolicyLines> resultArray = (ArrayList<PolicyLines>) q.getResultList();
        PolicyLines result = resultArray.get(0);
        return ResponseEntity.ok().body(result);
    }


    public ResponseEntity searchPolicyLine(PolicyLines policyLine) {
        PolicyLines result = (PolicyLines) em.createQuery("select p from PolicyLines p WHERE p.policyId = " + policyLine.getPolicyId()).getSingleResult();
        return ResponseEntity.ok().body(result);
    }


    public ResponseEntity getObjectRisksConfig(String query) {
        Query q = em.createQuery(query);

        ArrayList<ObjectRisksConfig> resultArray = (ArrayList<ObjectRisksConfig>) q.getResultList();
        return ResponseEntity.ok().body(resultArray);
    }


    public ResponseEntity createCustomer(Customers customer) {
        em.persist(customer);
        return ResponseEntity.ok().body(customer);
    }


    public ResponseEntity deleteCustomer(Customers customer) {
        em.createQuery("delete from Customers c where c.customerId = " + customer.getCustomerId()).executeUpdate();
        return ResponseEntity.ok().body(customer);
    }


    public ResponseEntity modifyCustomer(Customers customer) {
        em.merge(customer);
        return ResponseEntity.ok().body(customer);
    }


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

    public ResponseEntity verifyUserLogin(@RequestBody User user) {
        String userName = user.getName();
        String userPassword = user.getPassword();
        List<User> dbRecords = em.createQuery("select u from Users u", User.class).getResultList();
        for (User u : dbRecords) {
            if (u.getName().equals(userName) && u.getPassword().equals(userPassword)) {
                return ResponseEntity.ok().body(u);
            }
        }
        User notExist = new User();
        notExist.setName("NOT_EXIST");
        return ResponseEntity.ok().body(notExist);
    }

    public List customQuery(String query) {
        Query select = em.createQuery(query);
        List l = select.getResultList();
        return l;
    }


    public int updateQuery(String query) {
        int result = em.createQuery(query).executeUpdate();
        return result;
    }


    public ResponseEntity insertInsuredObject(InsuredObjects newInsuredObject) {
        em.persist(newInsuredObject);
        return ResponseEntity.ok().body(newInsuredObject);
    }


    public ResponseEntity getVehicleTypes(VehicleTypesConfig vehicleTypesConfig) {
        List<VehicleTypesConfig> resultList = (List<VehicleTypesConfig>) em.createQuery("select v from VehicleTypesConfig v WHERE v.productLineType = '" + vehicleTypesConfig.getProductLineType() + "'").getResultList();
        return ResponseEntity.ok().body(resultList);
    }


    public ResponseEntity createRisks(ObjectRisks risks) {
        em.persist(risks);
        return ResponseEntity.ok().build();
    }


    public ResponseEntity updateRisk(ObjectRisks risk) {
        em.merge(risk);
        return ResponseEntity.ok().body(risk);
    }


    public ResponseEntity updatePolicy(Policy policy) {
        em.merge(policy);
        return ResponseEntity.ok().body(policy);
    }


    public ResponseEntity updatePolicyLine(PolicyLines policyLine) {
        em.merge(policyLine);
        return ResponseEntity.ok().body(policyLine);
    }


    public ResponseEntity updateInsuredVehicle(InsuredObjects insuredObject) {
        em.merge(insuredObject);
        return ResponseEntity.ok().body(insuredObject);
    }


    public ResponseEntity getRisks(InsuredObjects insuredObject) {
        List<ObjectRisks> resultList = (List<ObjectRisks>) em.createQuery("select o from ObjectRisks o where o.objectId = " + insuredObject.getObjectId()).getResultList();
        return ResponseEntity.ok().body(resultList);
    }

    public ResponseEntity getAllObjectFlexfields() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ObjectFlexfieldsConfig> cq = cb.createQuery(ObjectFlexfieldsConfig.class);
        Root<ObjectFlexfieldsConfig> rootEntry = cq.from(ObjectFlexfieldsConfig.class);
        CriteriaQuery<ObjectFlexfieldsConfig> all = cq.select(rootEntry);
        TypedQuery<ObjectFlexfieldsConfig> allQuery = em.createQuery(all);
        return ResponseEntity.ok().body(allQuery.getResultList());
    }


    public ResponseEntity mergeObjectFlexfield(ObjectFlexfieldsConfig flexfield) {
        em.merge(flexfield);
        return ResponseEntity.ok().body(flexfield);
    }


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

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User addRoleToUser(String userName, String roleName) {
        User user = userRepository.findByUsername(userName);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        return user;
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findByUsername(userName);
    }
}
