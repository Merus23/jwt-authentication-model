package com.example.jwt_authentication_model.services;

import com.example.jwt_authentication_model.dtos.request.UserPermissionRequestDTO;
import com.example.jwt_authentication_model.exceptions.custom.BadRequestException;
import com.example.jwt_authentication_model.models.UserPermission;
import com.example.jwt_authentication_model.repositoties.UserPermissionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPermissionsService {
    private final UserPermissionsRepository userPermissionsRepository;

    public UserPermissionsService(UserPermissionsRepository userPermissionsRepository) {
        this.userPermissionsRepository = userPermissionsRepository;
    }

    public List<UserPermission> findAll(){
       return userPermissionsRepository.findAll();
    }

    public UserPermission findById(Long id){
        return userPermissionsRepository.findById(id).orElseThrow(()-> new RuntimeException("Permission not found!"));
    }

    public UserPermission findByName(String name){
        return  userPermissionsRepository.findByName(name).orElseThrow(()-> new RuntimeException("Permission not found!"));
    }

    public UserPermission create(UserPermissionRequestDTO permission){
        UserPermission entity = new UserPermission();

        entity.setDescription(permission.description());
        entity.setName(permission.name());

        return userPermissionsRepository.save(entity);
    }

    public UserPermission update(UserPermission permission) throws Exception{
        if(permission.getId() == null) throw new BadRequestException("O permission id cannot be null");
        UserPermission entity = findById(permission.getId());

        entity.setName(permission.getName());
        entity.setDescription(permission.getDescription());

        return userPermissionsRepository.save(entity);
    }

    public void delete (Long id){
        UserPermission permission = userPermissionsRepository.findById(id).orElseThrow(()->new RuntimeException("Permission not found"));
        userPermissionsRepository.delete(permission);
    }
}
