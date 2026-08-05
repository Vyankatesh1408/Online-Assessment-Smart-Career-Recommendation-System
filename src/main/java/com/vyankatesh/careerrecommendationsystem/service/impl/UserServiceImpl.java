package com.vyankatesh.careerrecommendationsystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vyankatesh.careerrecommendationsystem.dto.request.UserRequestDTO;
import com.vyankatesh.careerrecommendationsystem.dto.response.UserResponseDTO;
import com.vyankatesh.careerrecommendationsystem.entity.Role;
import com.vyankatesh.careerrecommendationsystem.entity.User;
import com.vyankatesh.careerrecommendationsystem.exception.DuplicateResourceException;
import com.vyankatesh.careerrecommendationsystem.exception.ResourceNotFoundException;
import com.vyankatesh.careerrecommendationsystem.mapper.UserMapper;
import com.vyankatesh.careerrecommendationsystem.repository.RoleRepository;
import com.vyankatesh.careerrecommendationsystem.repository.UserRepository;
import com.vyankatesh.careerrecommendationsystem.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

	private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
	private RoleRepository roleRepository;
	private UserMapper userMapper;
	

    public UserServiceImpl(UserRepository userRepository,
            RoleRepository roleRepository,
            UserMapper userMapper,
            BCryptPasswordEncoder passwordEncoder) {

    	this.userRepository = userRepository;
    	this.roleRepository = roleRepository;
    	this.userMapper = userMapper;
    	this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {

        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new DuplicateResourceException(
                    "User with email '" + userRequestDTO.getEmail() + "' already exists.");
        }

        // Convert DTO to Entity
        User user = userMapper.toEntity(userRequestDTO);
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign default role
        Role role = roleRepository.findByRoleName("STUDENT")
                .orElseThrow(() ->
                        new ResourceNotFoundException("Default role STUDENT not found."));

        user.setRole(role);

        // Save Entity
        User savedUser = userRepository.save(user);

        // Convert Entity to Response DTO
        return userMapper.toResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        return userMapper.toResponseDTO(user);
    }
    @Override
    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }
    

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        if (!existingUser.getEmail().equals(userRequestDTO.getEmail())
                && userRepository.existsByEmail(userRequestDTO.getEmail())) {

            throw new DuplicateResourceException(
                    "User with email '" + userRequestDTO.getEmail() + "' already exists.");
        }

        existingUser.setFullName(userRequestDTO.getFullName());
        existingUser.setEmail(userRequestDTO.getEmail());
        existingUser.setPassword(
        	    passwordEncoder.encode(userRequestDTO.getPassword())
        	);
        User updatedUser = userRepository.save(existingUser);

        return userMapper.toResponseDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        userRepository.delete(existingUser);
    }
}