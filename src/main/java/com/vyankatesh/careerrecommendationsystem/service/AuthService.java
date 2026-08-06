package com.vyankatesh.careerrecommendationsystem.service;

import com.vyankatesh.careerrecommendationsystem.dto.request.LoginRequestDTO;
import com.vyankatesh.careerrecommendationsystem.dto.response.LoginResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

}