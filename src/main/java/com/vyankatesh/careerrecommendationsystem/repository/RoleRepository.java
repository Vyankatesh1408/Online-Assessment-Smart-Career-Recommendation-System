package com.vyankatesh.careerrecommendationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vyankatesh.careerrecommendationsystem.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	
	boolean existsByRoleName(String roleName);
	
}