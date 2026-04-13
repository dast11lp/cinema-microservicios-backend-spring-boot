package com.cinema.auth.services;

import com.cinema.auth.entities.Role;
import com.cinema.auth.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role findById (Long id) {
		return this.roleRepository.findById(id).orElse(null);
	}
	
	public void save(Role role) {
		this.roleRepository.save(role);
	}
	
	public void deleteById(Long id) {
		this.roleRepository.deleteById(id);
	}
}
