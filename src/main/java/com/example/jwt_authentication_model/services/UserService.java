package com.example.jwt_authentication_model.services;

import com.example.jwt_authentication_model.dtos.request.UserRequestDTO;
import com.example.jwt_authentication_model.models.User;
import com.example.jwt_authentication_model.models.UserPermission;
import com.example.jwt_authentication_model.repositoties.UserPermissionsRepository;
import com.example.jwt_authentication_model.repositoties.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserPermissionsRepository userPermissionsRepository;

    public UserService(UserRepository userRepository, UserPermissionsRepository userPermissionsRepository) {
        this.userRepository = userRepository;
        this.userPermissionsRepository = userPermissionsRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public User create(UserRequestDTO user){
        User entity = new User();

        entity.setName(user.name());
        entity.setEmail(user.email());
        entity.setPassword(user.password());

        UserPermission permission = userPermissionsRepository.findByName(user.permission()).orElseThrow();
        entity.setPermission(permission);
        return userRepository.save(entity);
    }

    public User update(UserRequestDTO user){
        if (user.id().isEmpty()) throw new IllegalArgumentException("The user id cannot be null");

        User entity = findById(user.id().orElseThrow());
        entity.setName(user.name());
        entity.setEmail(user.email());
        entity.setPassword(user.password());
        UserPermission permission = userPermissionsRepository.findByName(user.permission()).orElseThrow();
        entity.setPermission(permission);

        return userRepository.save(entity);
    }

    public void delete (Long id){
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        userRepository.delete(user);
    }





}
