package com.application.psm.service;

import java.util.List;

import com.application.psm.model.Postulant;

public interface PostulantService {
	
	public List<Postulant> getAllPostulants();
	
	public Postulant savePostulant(Postulant postulant);
	
	public void deletePostulant(Long id);
	
	public Postulant editPostulant(Postulant postulant);
	
	public Postulant getUserById(Long id);
		
	public boolean checkPostulant(String name);
	
	public Postulant findByName (String name);
	
	//public String getUserRole (String username);
	
}
