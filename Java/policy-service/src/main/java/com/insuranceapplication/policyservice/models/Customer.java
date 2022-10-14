package com.insuranceapplication.policyservice.models;


import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
    private Integer id;
    private String name;
    private String pesel;
    private String address;
    private Date birthDate;
    private BigInteger phoneNum;

    public Customer(Integer id, String name, String pesel, String address, Date birthDate, BigInteger phoneNum) {
        this.id = id;
        this.name = name;
        this.pesel = pesel;
        this.address = address;
        this.birthDate = birthDate;
        this.phoneNum = phoneNum;
    }

    public Customer() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}
