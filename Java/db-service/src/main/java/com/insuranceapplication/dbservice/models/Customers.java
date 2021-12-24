package com.insuranceapplication.dbservice.models;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "customers")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_id_seq")
    @SequenceGenerator(name = "cust_id_seq", allocationSize = 1)

    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "name")
    private String name;
    @Column(name = "pesel")
    private String pesel;
    @Column(name = "address")
    private String address;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "phone_num")
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
