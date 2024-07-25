package com.shopmanagement.user.service;

import com.shopmanagement.user.entity.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserServices {

    Users createUser(Users users);

    List<Users> getUsers();

    Optional<Users> getUser(Integer userId);

    ResponseEntity<?> loginUser(String userName, String password);
}
