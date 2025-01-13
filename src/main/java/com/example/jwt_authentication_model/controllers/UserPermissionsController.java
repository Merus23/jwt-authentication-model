package com.example.jwt_authentication_model.controllers;

import com.example.jwt_authentication_model.dtos.UserPermissions.UserPermissionCreateDTO;
import com.example.jwt_authentication_model.models.User;
import com.example.jwt_authentication_model.models.UserPermission;
import com.example.jwt_authentication_model.services.UserPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config/permissions")
public class UserPermissionsController {

    @Autowired
    private UserPermissionsService userPermissionsService;

    @GetMapping(value = "/")
    public ResponseEntity<List<UserPermission>> findAllPermissions() {
        return ResponseEntity.ok(userPermissionsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPermission> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(userPermissionsService.findById(id));
    }

    @PostMapping(value = "/")
    public ResponseEntity<UserPermission> createPermission(@RequestBody UserPermissionCreateDTO permission) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userPermissionsService.create(permission));
    }

    @PutMapping(value = "/")
    public UserPermission updatePermission(@RequestBody UserPermission permission) throws Exception {
        return userPermissionsService.update(permission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePermission(@PathVariable(value = "id") Long id) {
        userPermissionsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}