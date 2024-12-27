package com.social.LongInstagram.controller;



import com.social.LongInstagram.config.JWTTokenGeneratorFilter;
import com.social.LongInstagram.config.JwtTokenValidationFilter;
import com.social.LongInstagram.dto.AuthenticationRequest;
import com.social.LongInstagram.entities.User;
import com.social.LongInstagram.exception.UserException;
import com.social.LongInstagram.repository.UserRepository;
import com.social.LongInstagram.sercurity.JwtTokenProvider;
import com.social.LongInstagram.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@AllArgsConstructor
@RestController
public class AuthenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private JWTTokenGeneratorFilter jwtTokenGeneratorFilter;

    @PostMapping("/signUp")
    public User signUp(@RequestBody User user) throws UserException {
        User createdUser = userService.registerUser(user);
        System.out.println("Created user: " + createdUser); // Debug log
        return createdUser; // Trả về trực tiếp đối tượng
    }

    @PostMapping("/signin")
    public ResponseEntity<User> signinHandler(@RequestBody AuthenticationRequest auth) throws BadCredentialsException {
        Optional<User> opt = userRepository.findByEmail(auth.getEmail());
            if (opt.isPresent()){
                return new ResponseEntity<User>(opt.get(),HttpStatus.ACCEPTED);
            }
                throw  new BadCredentialsException("Invalid email or password");
    }
}