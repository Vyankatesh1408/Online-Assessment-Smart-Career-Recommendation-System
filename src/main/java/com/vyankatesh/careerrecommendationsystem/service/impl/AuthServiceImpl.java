package com.vyankatesh.careerrecommendationsystem.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vyankatesh.careerrecommendationsystem.dto.request.LoginRequestDTO;
import com.vyankatesh.careerrecommendationsystem.dto.response.LoginResponseDTO;
import com.vyankatesh.careerrecommendationsystem.entity.User;
import com.vyankatesh.careerrecommendationsystem.exception.ResourceNotFoundException;
import com.vyankatesh.careerrecommendationsystem.repository.UserRepository;
import com.vyankatesh.careerrecommendationsystem.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid email or password"));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Invalid email or password");
        }

        LoginResponseDTO response = new LoginResponseDTO();

        response.setMessage("Login Successful");
        response.setUserId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRoleName(user.getRole().getRoleName());

        return response;
    }
}