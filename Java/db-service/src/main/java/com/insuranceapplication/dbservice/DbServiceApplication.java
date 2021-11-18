package com.insuranceapplication.dbservice;

import com.insuranceapplication.dbservice.globals.Variables;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DbServiceApplication {
	public static void main(String[] args) {
		Variables.init();
		SpringApplication.run(DbServiceApplication.class, args);
	}

}
