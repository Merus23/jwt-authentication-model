package com.example.jwt_authentication_model.services;

import com.example.jwt_authentication_model.models.User;
import com.example.jwt_authentication_model.models.UserPermission;
import com.example.jwt_authentication_model.repositoties.UserPermissionsRepository;
import com.example.jwt_authentication_model.repositoties.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserPermissionsRepository userPermissionsRepository;

    @InjectMocks
    private UserService userService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}