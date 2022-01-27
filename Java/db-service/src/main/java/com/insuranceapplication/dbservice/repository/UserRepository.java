package com.insuranceapplication.dbservice.repository;

import com.insuranceapplication.dbservice.models.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
     User findByUsername(String name);
}
