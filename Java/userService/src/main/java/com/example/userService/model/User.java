package com.example.userService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "User")
/*encja(entity) to odpowiednik tabeli w bazie danych*/
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "USER_ID")
    private int id;
    @Column(name = "PASSWORD")
    private String password;



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
