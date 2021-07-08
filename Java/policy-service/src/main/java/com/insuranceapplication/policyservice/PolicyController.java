package com.insuranceapplication.policyservice;

import com.google.gson.Gson;
import com.insuranceapplication.policyservice.models.InsuredObject;
import com.insuranceapplication.policyservice.models.Policy;
import com.insuranceapplication.policyservice.models.PolicyLine;
import com.insuranceapplication.policyservice.models.Vehicles;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PolicyController {

    //function adding new policy into db, it requests in body data according to Policy model
    @PostMapping("/createpolicy")
    public void createPolicy(@RequestBody Policy newPolicy){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolicyService");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(newPolicy);
        em.getTransaction().commit();
        em.close();
        emf.close();
        System.out.println(newPolicy);
    }

    //function adding new policy line into db, it requests in body data according to PolicyLine model
    @PostMapping("/createpolicyline")
    public void createPolicyLine(@RequestBody PolicyLine newPolicyLine){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolicyService");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(newPolicyLine);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    //function adding new insured object into db, it requests in body data according to InsuredObject model
    @PostMapping("/createinsuredobject")
    public void createInsuredObject(@RequestBody InsuredObject insuredObject){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PolicyService");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(insuredObject);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @GetMapping("/getvehicles")
    @ResponseBody
    public String getVehicles(@RequestBody Vehicles vehicles){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolicyService");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select distinct v.engine from Vehicles v");
        if(vehicles.getVehicle_type()==null){
            query = entityManager.createQuery("select distinct v.vehicle_type from Vehicles v");
        }
        else if(vehicles.getBrand()==null){
            query = entityManager.createQuery("select distinct v.brand from Vehicles v");
        }

        ArrayList<String> results = (ArrayList<String>) query.getResultList();
        results.forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
        return new Gson().toJson(results);
    }

    @GetMapping("/getlastpolicyno")
    @ResponseBody
    public int getLastPolicyNo(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolicyService");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select i.policy_id from Policy i");
        int lastPolicyNo = query.getFirstResult();
//        SELECT * FROM Table ORDER BY ID DESC LIMIT 1

        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
        return lastPolicyNo;
    }

//    @RequestMapping(value = "getvehicles", method = RequestMethod.GET)
//    @ResponseBody
//    public String parseToJson(Object body){
//        List<Vehicles> vehicles =
//        return new Gson().toJson(body);
//    }

}