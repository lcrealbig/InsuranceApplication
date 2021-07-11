package com.insuranceapplication.userservice.main;


import com.insuranceapplication.userservice.model.Users;
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
    public ResponseEntity verifyUserLogin(@RequestParam("id") String id,
                                          @RequestParam("password") String password) {
   
        /* getting a list of records from dataBase using entity.*/
        List<Users> dbRecords = em.createQuery("select u from Users u", Users.class).getResultList();
        for (Users user : dbRecords) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                return ResponseEntity.ok().body(user);
            }
        }
        Users notExist = new Users();
        notExist.setName("NOT_EXIST");
        return ResponseEntity.ok().body(notExist);
    }

    @GetMapping("/showStatus")
    public String showStatus() {
        String online = "Server is online.";
        return online;
    }

}
