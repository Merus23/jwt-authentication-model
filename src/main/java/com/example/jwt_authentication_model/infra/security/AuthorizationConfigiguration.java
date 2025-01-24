package com.example.jwt_authentication_model.infra.security;

import com.example.jwt_authentication_model.repositoties.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationConfigiguration implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthorizationConfigiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("loadUserByUsername: User not found"));
    }
}
