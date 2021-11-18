package com.customerservice.customerservice.model;

import java.math.BigInteger;
import java.util.Date;


public class Customers {

    //spraw generator id korzystal z sequencera.

    private int customer_id;
    private String name;
    private String pesel;
    private String address;
    private Date birthDate;
    private BigInteger phoneNum;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPesel() {
        return pesel;
    }
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public BigInteger getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(BigInteger phoneNum) {
        this.phoneNum = phoneNum;
    }
    public int getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
