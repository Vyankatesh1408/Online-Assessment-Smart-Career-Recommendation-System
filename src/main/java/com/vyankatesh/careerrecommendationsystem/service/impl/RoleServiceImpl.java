package com.vyankatesh.careerrecommendationsystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vyankatesh.careerrecommendationsystem.entity.Role;
import com.vyankatesh.careerrecommendationsystem.exception.DuplicateResourceException;
import com.vyankatesh.careerrecommendationsystem.exception.ResourceNotFoundException;
import com.vyankatesh.careerrecommendationsystem.repository.RoleRepository;
import com.vyankatesh.careerrecommendationsystem.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {

        if (roleRepository.existsByRoleName(role.getRoleName())) {
            throw new DuplicateResourceException(
                    "Role '" + role.getRoleName() + "' already exists.");
        }

        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Long id) {
    	return roleRepository.findById(id)
    	        .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(Long id, Role role) {

        Role existingRole = roleRepository.findById(id).orElse(null);

        if (existingRole != null) {
            existingRole.setRoleName(role.getRoleName());
            return roleRepository.save(existingRole);
        }

        return null;
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}