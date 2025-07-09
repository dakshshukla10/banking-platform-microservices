package com.example.auth.service;

import com.example.auth.dto.RegisterRequest;
import com.example.auth.dto.RegisterResponse;

public interface UserService {

    /**
     * Registers a new user and returns a lightweight response DTO.
     */
    RegisterResponse register(RegisterRequest request);

    // add more methods later (findById, list, etc.)
}