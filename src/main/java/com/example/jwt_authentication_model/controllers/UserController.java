package com.example.jwt_authentication_model.controllers;

import com.example.jwt_authentication_model.dtos.User.UserRequestDTO;
import com.example.jwt_authentication_model.models.User;
import com.example.jwt_authentication_model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping(value = "/")
    public ResponseEntity<User> createUser(@RequestBody UserRequestDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @PutMapping(value = "/")
    public User updateUser(@RequestBody UserRequestDTO user) throws Exception {
        if (user.id().isEmpty()) throw new Exception("User id is required");

        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable(value = "id") Long id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
