package com.vyankatesh.careerrecommendationsystem.mapper;

import org.springframework.stereotype.Component;

import com.vyankatesh.careerrecommendationsystem.dto.request.UserRequestDTO;
import com.vyankatesh.careerrecommendationsystem.dto.response.UserResponseDTO;
import com.vyankatesh.careerrecommendationsystem.entity.User;

@Component
public class UserMapper {

    public User toEntity(UserRequestDTO dto) {

        User user = new User();

        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {

        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setRoleName(user.getRole().getRoleName());

        return dto;
    }

}