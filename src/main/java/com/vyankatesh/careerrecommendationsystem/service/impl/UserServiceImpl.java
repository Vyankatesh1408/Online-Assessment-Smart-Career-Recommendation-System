package com.vyankatesh.careerrecommendationsystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vyankatesh.careerrecommendationsystem.entity.User;
import com.vyankatesh.careerrecommendationsystem.exception.DuplicateResourceException;
import com.vyankatesh.careerrecommendationsystem.exception.ResourceNotFoundException;
import com.vyankatesh.careerrecommendationsystem.repository.UserRepository;
import com.vyankatesh.careerrecommendationsystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException(
                    "User with email '" + user.getEmail() + "' already exists.");
        }

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        if (!existingUser.getEmail().equals(user.getEmail())
                && userRepository.existsByEmail(user.getEmail())) {

            throw new DuplicateResourceException(
                    "User with email '" + user.getEmail() + "' already exists.");
        }

        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        userRepository.delete(existingUser);
    }
}