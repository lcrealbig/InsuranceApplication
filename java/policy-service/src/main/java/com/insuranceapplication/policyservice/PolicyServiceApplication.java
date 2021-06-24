package com.insuranceapplication.policyservice;

import net.minidev.json.JSONArray;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class PolicyServiceApplication {
	

	private static void addPolicy(Policy policy) {
		policy.setOwner_id(115);
		policy.setType("Car");
		policy.setStatus("Active");
		policy.setStart_date("2021-09-12");
		policy.setEnd_date("2021-10-12");
		policy.setProduct_type("OC");
		policy.setAlt_no("125CAR");
	}

	public static void main(String[] args) {
		SpringApplication.run(PolicyServiceApplication.class, args);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Policy");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Policy new_policy = new Policy();
		addPolicy(new_policy);

		em.persist(new_policy);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
