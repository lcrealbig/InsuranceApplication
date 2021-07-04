package com.example.userService.model;

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
    private int id;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ADRESS")
    private String adress;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "DATE")
    private Date date;
    @Column(name = "PESEL")
    private int pesel;
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

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
