package com.ekabotdev.taskmanager.service;

import com.ekabotdev.taskmanager.dto.LoginRequest;
import com.ekabotdev.taskmanager.dto.LoginResponse;
import com.ekabotdev.taskmanager.dto.RegisterRequest;
import com.ekabotdev.taskmanager.dto.UserResponse;
import org.springframework.stereotype.Service;


public interface UserService {
    UserResponse register(RegisterRequest register);
    LoginResponse login(LoginRequest loginRequest);
}
