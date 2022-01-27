package com.insuranceapplication.dbservice.interfaces;

import com.insuranceapplication.dbservice.models.authentication.Role;
import com.insuranceapplication.dbservice.models.authentication.User;


public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    User addRoleToUser(String username, String rolename);
    User getUser(String username);
}
