package com.example.auth.service.impl;

import com.example.auth.domain.User;
import com.example.auth.dto.RegisterRequest;
import com.example.auth.dto.RegisterResponse;
import com.example.auth.repository.UserRepository;
import com.example.auth.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;   // bean provided in SecurityConfig

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        User user = new User();
        // ⇢ assign a fresh UUID primary key
        user.setId(UUID.randomUUID());

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // ⇢ every new account starts with the regular role
        user.setRoles("ROLE_USER");

        User saved = userRepository.save(user);

        // RegisterResponse(id, message)  – adjust if your constructor is different
        return new RegisterResponse(saved.getId(), "User registered");
    }
}