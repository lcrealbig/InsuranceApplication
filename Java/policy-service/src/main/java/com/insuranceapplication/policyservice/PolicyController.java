package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.models.Policy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@RestController
public class PolicyController {

    //function adding new policy into db, it requests in body data according to policy model
    @PostMapping("/createpolicy")
    public void createPolicy(@RequestBody Policy new_policy){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Policy");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new_policy);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}