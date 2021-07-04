package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.models.InsuredObject;
import com.insuranceapplication.policyservice.models.Policy;
import com.insuranceapplication.policyservice.models.PolicyLine;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

    @PostMapping("/testJson")
    public void testClient(@RequestBody Object obj){

        System.out.println(obj.toString());

    }

}