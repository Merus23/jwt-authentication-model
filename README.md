# JWT Authentication Model

## Introduction
This is a simple project that demonstrates how to use JWT for authentication in a Spring Boot application.

## Models
- **User**: Represents a user in the system. It has the following fields:
  - id: The user's id.
  - username: The user's username.
  - password: The user's password.
  - email: The user's email.
  - roles: The user's roles.

- **Role**: Represents a role in the system. It has the following fields:
  - id: The role's id.
  - name: The role's name. Role name is always safe as uppercase.
  - description: The role's description.
  
### Migration
The project uses Flyway to manage the database schema. The migration files are located in the `src/main/resources/db/migration` directory.

## Customizing your Authentication
By default, the authentication is done by the username and his password.
You can change it, by implementing the `UserDetailsService` interface and override the `loadUserByUsername` method (e.g.: for use his email).
You need change the LoginRequestDTO and the AuthenticationController to use the new implementation.
You can find this files at:

UserDetailService: `src/main/java/com/example/jwt_authentication_model/services/UserDetailsServiceImpl.java`
```java
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("loadUserByUsername: User not found"));
    }
```

AuthenticationController: `src/main/java/com/example/jwt_authentication_model/controllers/AuthenticationController.java`
```java
@PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequest) throws Exception {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = this.tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
```

LoginRequestDTO: `src/main/java/com/example/jwt_authentication_model/dto/request/LoginRequestDTO.java`
```java
public record LoginRequestDTO(String email, String password) {
}
```


## application.yml
```yml
spring:
  application:
    name={your-application-name}

  flyway:
    baseline-on-migrate: true
    baseline-version: 1

  datasource:
    driver-class-name: {your-database-driver} 
    url: {your-database-url}
    username: {your-database-username}
    password: {your-database-password}

  jpa:
    open-in-view: false
    hibernate:
      ddl-auto: none
    properties:
      show-sql: true

api:
  security:
    jwt:
      secret: {your-jwt-secret}


logging:
  level:
    org.springframework.security: DEBUG
```
