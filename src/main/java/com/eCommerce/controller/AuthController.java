package com.eCommerce.controller;

import com.eCommerce.dto.LoginDto;
import com.eCommerce.dto.SignUpDto;
import com.eCommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController

public class AuthController {

    AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/auth/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginDto loginDto)
    {
        return authService.login(loginDto);
    }

    @PostMapping("/auth/signUp")
    @ResponseStatus(HttpStatus.OK)
    public String register(@RequestBody SignUpDto signUpDto)
    {
        return authService.signup(signUpDto);
    }


}
