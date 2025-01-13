package com.example.jwt_authentication_model.controllers;

import com.example.jwt_authentication_model.dtos.UserPermissions.UserPermissionCreateDTO;
import com.example.jwt_authentication_model.models.UserPermission;
import com.example.jwt_authentication_model.services.UserPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config/permissions")
public class UserPermissionsController {

    @Autowired
    private UserPermissionsService userPermissionsService;

    @GetMapping
    public List<UserPermission> findAllPermissions() {
        return userPermissionsService.findAll();
    }

    @GetMapping("/{id}")
    public UserPermission findById(@PathVariable(value = "id") Long id) {
        return userPermissionsService.findById(id);
    }

    @PostMapping
    public UserPermission createPermission(@RequestBody UserPermissionCreateDTO permission) {
        return userPermissionsService.create(permission);
    }

    @PutMapping
    public UserPermission updatePermission(@RequestBody UserPermission permission) throws Exception {
        return userPermissionsService.update(permission);
    }

    @DeleteMapping("/{id}")
    public void deletePermission(@PathVariable(value = "id") Long id) {
        userPermissionsService.delete(id);
    }
}