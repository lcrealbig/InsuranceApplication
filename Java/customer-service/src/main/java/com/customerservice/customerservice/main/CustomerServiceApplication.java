package com.customerservice.customerservice.main;

import com.customerservice.customerservice.globals.Variables;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerServiceApplication {

	public static void main(String[] args) {
		Variables.init();
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
