package com.insuranceapplication.userservice.main;

import com.insuranceapplication.userservice.globals.Variables;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.insuranceapplication.userservice.model")
@SpringBootApplication
@EnableAutoConfiguration
public class UserServiceApplication {

    public static void main(String[] args) {
        Variables.init();
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
