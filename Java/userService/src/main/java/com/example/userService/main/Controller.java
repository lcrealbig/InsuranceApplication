package com.example.userService.main;


import com.example.userService.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@RestController
public class Controller {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @PostMapping("/verify")
    public ResponseEntity<User> verifyUserLogin(@RequestParam("id") int id,
                                                @RequestParam("password") String password) {

        /* getting a list of records from dataBase using entity.*/
        List<User> dbRecords = em.createQuery("select u from User u", User.class).getResultList();
        for (User user : dbRecords) {
            if (user.getId() == id && user.getPassword().equals(password)) {
                return ResponseEntity.ok().body(user);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/showStatus")
    public String showStatus() {
        String online = "Server is online.";
        return online;
    }

}
