package com.example.jwt_authentication_model.repositoties;

import com.example.jwt_authentication_model.models.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPermissionsRepository extends JpaRepository<UserPermission, Long> {
    Optional<UserPermission> findByName(String name);

}
