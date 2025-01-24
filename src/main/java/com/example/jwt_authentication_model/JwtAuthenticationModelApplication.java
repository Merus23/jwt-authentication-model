package com.example.jwt_authentication_model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.jwt_authentication_model")
public class JwtAuthenticationModelApplication {

	public static void main(String[] args) {

		SpringApplication.run(JwtAuthenticationModelApplication.class, args);
	}

}
