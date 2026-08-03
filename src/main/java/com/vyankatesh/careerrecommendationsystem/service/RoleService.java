package com.vyankatesh.careerrecommendationsystem.service;

import java.util.List;

import com.vyankatesh.careerrecommendationsystem.entity.Role;

public interface RoleService {

    Role saveRole(Role role);

    Role getRoleById(Long id);

    List<Role> getAllRoles();

    Role updateRole(Long id, Role role);

    void deleteRole(Long id);

}