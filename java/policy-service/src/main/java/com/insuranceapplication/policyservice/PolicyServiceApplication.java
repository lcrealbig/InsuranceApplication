package com.insuranceapplication.policyservice;

import com.insuranceapplication.policyservice.globals.Variables;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class PolicyServiceApplication {

	public static void main(String[] args) {
		Variables.init();
		SpringApplication.run(PolicyServiceApplication.class, args);

	}
}
