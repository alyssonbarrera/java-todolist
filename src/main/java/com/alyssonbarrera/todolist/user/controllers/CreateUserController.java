package com.alyssonbarrera.todolist.user.controllers;

import com.alyssonbarrera.todolist.user.entities.User;
import com.alyssonbarrera.todolist.user.services.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class CreateUserController {

    @Autowired
    private CreateUserService createUserService;

    @PostMapping("")
    public ResponseEntity handle(@RequestBody User user) {
        User result = this.createUserService.execute(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
