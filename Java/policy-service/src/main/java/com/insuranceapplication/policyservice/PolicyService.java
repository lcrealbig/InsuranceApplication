package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.models.InsuredObject;
import com.insuranceapplication.policyservice.models.Policy;
import com.insuranceapplication.policyservice.models.PolicyLine;
import com.insuranceapplication.policyservice.models.Vehicles;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;

@Service
public class PolicyService {

    public void createPolicy(Policy newPolicy){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolicyService");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(newPolicy);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    //function adding new policy line into db, it requests in body data according to PolicyLine model
    public void createPolicyLine(PolicyLine newPolicyLine){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolicyService");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(newPolicyLine);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    //function adding new insured object into db, it requests in body data according to InsuredObject model
    public void createInsuredObject(InsuredObject insuredObject){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolicyService");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(insuredObject);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }


    public ResponseEntity getVehicles(Vehicles vehicles){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolicyService");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = null;
        if(vehicles.getVehicle_type() == null){
            query = entityManager.createQuery("select distinct v.vehicle_type from Vehicles v");
        } else if(vehicles.getBrand() == null){
            query = entityManager.createQuery("select distinct v.brand from Vehicles v");
        } else if(vehicles.getVehicle_model() == null){
            query = entityManager.createQuery("select distinct v.vehicle_model from Vehicles v WHERE v.brand = '" + vehicles.getBrand() + "'");
        } else if(vehicles.getGeneration() == null){
            query = entityManager.createQuery("select distinct v.generation from Vehicles v WHERE v.vehicle_model = '" + vehicles.getVehicle_model() +
                    "' AND v.brand = '" + vehicles.getBrand() + "'");
        } else if(vehicles.getEngine_type() == null){
            query = entityManager.createQuery("select distinct v.engine_type from Vehicles v WHERE v.generation = '" + vehicles.getGeneration() +
                    "' and v.vehicle_model = '" + vehicles.getVehicle_model() + "' AND v.brand = '" + vehicles.getBrand() + "'");
        } else if(vehicles.getEngine() == null){
            query = entityManager.createQuery("select distinct v.engine from Vehicles v WHERE v.engine_type = '" + vehicles.getEngine_type() +
                    "' and v.vehicle_model = '" + vehicles.getVehicle_model() + "' AND v.brand = '" + vehicles.getBrand() +
                    "' AND v.generation = '" + vehicles.getGeneration() + "'");
        } else {
            query = entityManager.createQuery("select distinct v.vehicle_id from Vehicles v WHERE v.engine_type = '" + vehicles.getEngine_type() +
                    "' and v.vehicle_model = '" + vehicles.getVehicle_model() + "' AND v.brand = '" + vehicles.getBrand() +
                    "' AND v.generation = '" + vehicles.getGeneration() + "' and v.engine = '"+vehicles.getEngine()+"'");
        }

        ArrayList<String> results = (ArrayList<String>) query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();

        return ResponseEntity.ok().body(results);
    }

    public static ResponseEntity getPolicy(Policy policy){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolicyService");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("select p from Policy p WHERE p.owner_id = '" + policy.getOwner_id() +
                "' and p.type = '" + policy.getType() + "' AND p.status = '" + policy.getStatus() +
                "' AND p.start_date = '" + policy.getStart_date() + "' and p.end_date = '" + policy.getEnd_date() +
                "'and p.product_type = '" + policy.getProduct_type() + "'");

        ArrayList<String> results = (ArrayList<String>) query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();

        return ResponseEntity.ok().body(results);
    }
}
