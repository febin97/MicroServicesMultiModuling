package com.stackroute.controller;


import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistException;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/user-service")
public class UserController {
  UserService userService;
  @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        ResponseEntity responseEntity;
        try {
            userService.saveUser(user);
            responseEntity = new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
        } catch (UserAlreadyExistException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            }
       return responseEntity;
    }
    @GetMapping("users")
    public ResponseEntity<?>getAllUser(){

      return new ResponseEntity<List<User>>(userService.getAllUser() , HttpStatus.OK);
    }




}
