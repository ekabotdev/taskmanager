package com.ekabotdev.taskmanager.service;

import com.ekabotdev.taskmanager.dto.auth.LoginRequest;
import com.ekabotdev.taskmanager.dto.auth.LoginResponse;
import com.ekabotdev.taskmanager.dto.auth.RegisterRequest;
import com.ekabotdev.taskmanager.dto.auth.UserResponse;


public interface UserService {
    UserResponse register(RegisterRequest register);
    LoginResponse login(LoginRequest loginRequest);
}
