package com.example.userService.main;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;


@RestController
public class Controller {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @PostMapping("/insert")
    public void verifyUserLogin(@RequestParam("id") int id,
                                @RequestParam("password") String password
    ) {
        /* getting a list of records from dataBase using entity.*/
        List<User> dbRecords = em.createQuery("select u from User u", User.class).getResultList();
        System.out.println("");
    }

    @GetMapping("hi")
    public String hi() {
        String hi = "hi";
        return hi;
    }
}
