package com.tpe.springbootdemo181.controller;

import com.tpe.springbootdemo181.domain.User;
import com.tpe.springbootdemo181.dto.UserRequest;
import com.tpe.springbootdemo181.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register") // http://localhost:8080/register
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping   // http://localhost:8080/register
    public ResponseEntity<String> register(@Valid @RequestBody UserRequest userDTO){

        userService.saveUser(userDTO);

        return new ResponseEntity<>("Registration success.", HttpStatus.CREATED);

    }
}
