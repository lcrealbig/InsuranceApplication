package com.insuranceapplication.dbservice.models;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "CUSTOMERS")
public class Customers {
    private Integer customerId;
    private String name;
    private String pesel;
    private String address;
    private Date birthDate;
    private BigInteger phoneNum;

    public Customers(Integer customerId, String name, String pesel, String address, Date birthDate, BigInteger phoneNum) {
        this.customerId = customerId;
        this.name = name;
        this.pesel = pesel;
        this.address = address;
        this.birthDate = birthDate;
        this.phoneNum = phoneNum;
    }

    public Customers() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_id_seq")
    @SequenceGenerator(name = "cust_id_seq", allocationSize = 1)
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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
