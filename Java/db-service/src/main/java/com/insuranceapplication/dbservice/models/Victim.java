package com.insuranceapplication.dbservice.models;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
@Entity
@Table(name = "Victim")
public class Victim {

    private Integer id;
    private String name;
    private String pesel;
    private String address;
    private Date birthDate;
    private BigInteger phoneNum;

    public Victim(Integer id, String name, String pesel, String address, Date birthDate, BigInteger phoneNum) {
        this.id = id;
        this.name = name;
        this.pesel = pesel;
        this.address = address;
        this.birthDate = birthDate;
        this.phoneNum = phoneNum;
    }

    public Victim() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_id_seq")
    @SequenceGenerator(name = "bill_id_seq", allocationSize = 1)

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}