package com.vyankatesh.careerrecommendationsystem.service;

import java.util.List;

import com.vyankatesh.careerrecommendationsystem.entity.User;

public interface UserService {

    User saveUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, User user);

    void deleteUser(Long id);

}