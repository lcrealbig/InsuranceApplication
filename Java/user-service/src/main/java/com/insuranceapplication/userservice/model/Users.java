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
    private String id;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ADDRESS")
    private String address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserPassword() { return userPassword; }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
