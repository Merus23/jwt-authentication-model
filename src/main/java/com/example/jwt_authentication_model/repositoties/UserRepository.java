package com.example.jwt_authentication_model.repositoties;

import com.example.jwt_authentication_model.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);
}
