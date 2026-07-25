package com.ekabotdev.taskmanager.service;

import com.ekabotdev.taskmanager.dto.RegisterRequest;
import com.ekabotdev.taskmanager.dto.UserResponse;
import org.springframework.stereotype.Service;


public interface UserService {
    UserResponse register(RegisterRequest register);
}
