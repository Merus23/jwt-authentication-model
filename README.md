# JWT Authentication Model

## Introduction

## Authentication
By default, the authentication is done by the username and his password.
You can change it, by implementing the `UserDetailsService` interface and override the `loadUserByUsername` method.
You need change the LoginRequestDTO and the AuthenticationController to use the new implementation.
You can find this files at:

UserDetailService :`src/main/java/com/example/jwt_authentication_model/services/UserDetailsServiceImpl.java`

AuthenticationController :`src/main/java/com/example/jwt_authentication_model/controllers/AuthenticationController.java`

LoginRequestDTO :`src/main/java/com/example/jwt_authentication_model/dto/request/LoginRequestDTO.java`

