package com.ekabotdev.taskmanager.controller;


import com.ekabotdev.taskmanager.dto.auth.LoginRequest;
import com.ekabotdev.taskmanager.dto.auth.LoginResponse;
import com.ekabotdev.taskmanager.dto.auth.RegisterRequest;
import com.ekabotdev.taskmanager.dto.auth.UserResponse;
import com.ekabotdev.taskmanager.service.userservice.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(
            @Valid @RequestBody RegisterRequest registerRequest) {

        return userService.register(registerRequest);
    }
    @PostMapping("/login")
    public LoginResponse loginUser( @Valid @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
    }

