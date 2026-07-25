package com.ekabotdev.taskmanager.controller;


import com.ekabotdev.taskmanager.dto.RegisterRequest;
import com.ekabotdev.taskmanager.dto.UserResponse;
import com.ekabotdev.taskmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
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
    }

