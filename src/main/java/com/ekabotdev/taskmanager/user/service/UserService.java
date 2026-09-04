package com.ekabotdev.taskmanager.user.service;

import com.ekabotdev.taskmanager.user.dto.LoginRequest;
import com.ekabotdev.taskmanager.user.dto.LoginResponse;
import com.ekabotdev.taskmanager.user.dto.RegisterRequest;
import com.ekabotdev.taskmanager.user.dto.UserResponse;


public interface UserService {
    UserResponse register(RegisterRequest register);
    LoginResponse login(LoginRequest loginRequest);
}
