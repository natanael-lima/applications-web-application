package com.application.psm.service;

import java.util.List;

import com.application.psm.model.User;


public interface UserService {
	
	List<User> getAllUsers();
	
	User saveEstudiante(User estudiante);
	
	void deleteEstudiante(Long id);
	
	User editEstudiante(User estudiante);
	
	User getEstudianteById(Long id);
}
