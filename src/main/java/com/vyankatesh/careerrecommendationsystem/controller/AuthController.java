package com.vyankatesh.careerrecommendationsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.vyankatesh.careerrecommendationsystem.dto.request.LoginRequestDTO;
import com.vyankatesh.careerrecommendationsystem.dto.response.LoginResponseDTO;
import com.vyankatesh.careerrecommendationsystem.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {

        return authService.login(loginRequestDTO);
    }

}