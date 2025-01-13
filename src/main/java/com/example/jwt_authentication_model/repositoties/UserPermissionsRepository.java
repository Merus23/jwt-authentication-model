package com.example.jwt_authentication_model.repositoties;

import com.example.jwt_authentication_model.models.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPermissionsRepository extends JpaRepository<UserPermission, Long> { }
