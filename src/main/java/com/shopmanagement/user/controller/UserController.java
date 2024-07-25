package com.shopmanagement.user.controller;

import com.shopmanagement.user.dto.LoginRequestDto;
import com.shopmanagement.user.entity.Users;
import com.shopmanagement.user.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/createUser")
    public Users createUser(@RequestBody Users users){
        System.out.println("in createUser controller");
        return userServices.createUser(users);
    }

    @GetMapping("/getUsers")
    public List<Users> getUsers(){
        return userServices.getUsers();
    }

    @GetMapping("/getUser/{userId}")
    public Optional<Users> getUser(@PathVariable Integer userId){
        return userServices.getUser(userId);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto dto){
        return userServices.loginUser(dto.getLoginName(),dto.getPassword());
    }
}
