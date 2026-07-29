package com.ekabotdev.taskmanager.service;


import com.ekabotdev.taskmanager.dto.LoginRequest;
import com.ekabotdev.taskmanager.dto.LoginResponse;
import com.ekabotdev.taskmanager.dto.RegisterRequest;
import com.ekabotdev.taskmanager.dto.UserResponse;
import com.ekabotdev.taskmanager.entity.User;
import com.ekabotdev.taskmanager.enums.Role;
import com.ekabotdev.taskmanager.exception.EmailAlreadyExistsException;
import com.ekabotdev.taskmanager.exception.InvalidCredentialsException;
import com.ekabotdev.taskmanager.exception.UsernameAlreadyExistsException;
import com.ekabotdev.taskmanager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    @Override
    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }

            if (userRepository.existsUserByEmail(request.getEmail())) {
                throw new EmailAlreadyExistsException("Email already exists.");
            }

                String encodedPassword = passwordEncoder.encode(request.getPassword());
                User user = new User();
                user.setUsername(request.getUsername());
                user.setEmail(request.getEmail());
                user.setPassword(encodedPassword);
                user.setRole(Role.USER);

                User savedUser = userRepository.save(user);

                UserResponse response = new UserResponse();
                response.setId(savedUser.getId());
                response.setUsername(savedUser.getUsername());
                response.setEmail(savedUser.getEmail());

                return response;

            }
            @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() ->
                new InvalidCredentialsException("invalid email or password"));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("invalid email or password");
        }
        String token = jwtService.generateToken(user);
        return new LoginResponse(token);
    }
        }
