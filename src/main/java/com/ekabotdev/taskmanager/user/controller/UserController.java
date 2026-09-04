package com.ekabotdev.taskmanager.user.controller;


import com.ekabotdev.taskmanager.user.dto.LoginRequest;
import com.ekabotdev.taskmanager.user.dto.LoginResponse;
import com.ekabotdev.taskmanager.user.dto.RegisterRequest;
import com.ekabotdev.taskmanager.user.dto.UserResponse;
import com.ekabotdev.taskmanager.user.service.UserService;
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

