package com.insuranceapplication.userservice.main;

import com.insuranceapplication.userservice.model.Users;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public ResponseEntity verifyUserLogin(@RequestBody JSONObject jsonObject) {
        String user_name = jsonObject.get("userName").toString();
        String user_password = jsonObject.get("userPassword").toString();
        /* getting a list of records from dataBase using entity.*/
        List<Users> dbRecords = em.createQuery("select u from Users u", Users.class).getResultList();
        for (Users user : dbRecords) {
            if (user.getUserName().equals(user_name) && user.getUserPassword().equals(user_password)) {
                return ResponseEntity.ok().body(user);
            }
        }
        Users notExist = new Users();
        notExist.setName("NOT_EXIST");
        return ResponseEntity.ok().body(notExist);
    }
}

