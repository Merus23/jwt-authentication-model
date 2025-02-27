package com.example.jwt_authentication_model.controllers;

import com.example.jwt_authentication_model.dtos.request.UserPermissionRequestDTO;
import com.example.jwt_authentication_model.models.UserPermission;
import com.example.jwt_authentication_model.services.UserPermissionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/config/permissions")
public class UserPermissionsController {
    private final UserPermissionsService userPermissionsService;

    public UserPermissionsController(UserPermissionsService userPermissionsService) {
        this.userPermissionsService = userPermissionsService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<UserPermission>> findAllPermissions() {
        return ResponseEntity.ok(userPermissionsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserPermission> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(userPermissionsService.findById(id));
    }

    @PostMapping(value = "/")
    public ResponseEntity<UserPermission> createPermission(@RequestBody UserPermissionRequestDTO permission) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userPermissionsService.create(permission));
    }

    @PutMapping(value = "/")
    public UserPermission updatePermission(@RequestBody UserPermission permission) throws Exception {
        return userPermissionsService.update(permission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePermission(@PathVariable(value = "id") Long id) {
        userPermissionsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}