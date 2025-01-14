package com.example.jwt_authentication_model.repositoties;

import com.example.jwt_authentication_model.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }
