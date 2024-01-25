package com.application.psm.service;

import java.util.List;

import com.application.psm.model.Role;


public interface RoleService {
	public Role findByIdRol(Long id);
	public List<Role> getRoles();
}
