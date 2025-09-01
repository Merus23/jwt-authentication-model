package com.example.jwt_authentication_model.services;

import com.example.jwt_authentication_model.dtos.request.UserRequestDTO;
import com.example.jwt_authentication_model.exceptions.custom.BadRequestException;
import com.example.jwt_authentication_model.exceptions.custom.NotFoundContentException;
import com.example.jwt_authentication_model.models.User;
import com.example.jwt_authentication_model.models.UserPermission;
import com.example.jwt_authentication_model.repositoties.UserPermissionsRepository;
import com.example.jwt_authentication_model.repositoties.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return userRepository.findById(id).orElseThrow(() -> new NotFoundContentException("The user with the id " + id + " was not found."));
    }

    public User create(UserRequestDTO user){
        User entity = new User();

        entity.setName(user.name());
        entity.setEmail(user.email());
        entity.setPassword(user.password());

        UserPermission permission = userPermissionsRepository.findByName(user.permission()).orElseThrow(() -> new NotFoundContentException("The permission" + user.permission() + "was not found."));
        entity.setPermission(permission);
        return userRepository.save(entity);
    }

    public User update(UserRequestDTO user){
        User entity = findById(user.id().orElseThrow(
                () -> new BadRequestException("The user id cannot be null. Please include the user id into your request.")));

        if (entity == null) throw new NotFoundContentException("The user with the id " + user.id().get() + " was not found.");

        entity.setName(user.name());
        entity.setEmail(user.email());
        entity.setPassword(user.password());
        UserPermission permission = userPermissionsRepository.findByName(user.permission()).orElseThrow();
        entity.setPermission(permission);

        return userRepository.save(entity);
    }

    public void delete (Long id){
        User user = userRepository.findById(id).orElseThrow(()->new BadRequestException("The user with the id "+id+" was not found."));
        userRepository.delete(user);
    }





}
