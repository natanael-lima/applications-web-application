package com.application.psm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.psm.model.Role;
import com.application.psm.repository.RoleRepository;
import com.application.psm.service.RoleService;


@Service
public class RoleServiceImp implements RoleService{
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByIdRol(Long id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id).orElse(null);
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return (List<Role>) roleRepository.findAll();
	}
}
