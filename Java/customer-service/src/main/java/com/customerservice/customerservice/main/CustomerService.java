package com.customerservice.customerservice.main;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerService {

    @Transactional
    public void addCustomer() {

    }

    @Transactional
    public void modifyCustomer() {
    }

    @Transactional
    public void dropCustomer() {
        
    }
}
