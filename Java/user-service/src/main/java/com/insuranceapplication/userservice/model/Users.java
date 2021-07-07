package com.insuranceapplication.userservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
/*encja(entity) to odpowiednik tabeli w bazie danych*/
@Table(name = "USERS")
public class Users {
    @Id
    @Column(name = "USER_ID")
    private long id;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ADRESS")
    private String adress;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "BIRTH_DATE")
    private Date date;
    @Column(name = "PESEL")
    private long pesel;
    /*zmapuj wszystkie kolumny usera i powinno dzialac*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getPesel() {
        return pesel;
    }

    public void setPesel(long pesel) {
        this.pesel = pesel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}