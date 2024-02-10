package com.application.psm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.psm.model.Postulant;
import com.application.psm.repository.PostulantRepository;
import com.application.psm.service.PostulantService;


@Service
public class PostulantServiceImp implements PostulantService{
	
	@Autowired
	private PostulantRepository postulantRepository;
	
	@Override
	public List<Postulant> getAllPostulants() {
		// TODO Auto-generated method stub
		return postulantRepository.findAll();
	}

	@Override
	public Postulant savePostulant(Postulant postulant) {
		// TODO Auto-generated method stub
		return postulantRepository.save(postulant);
	}

	@Override
	public void deletePostulant(Long id) {
		// TODO Auto-generated method stub
		postulantRepository.deleteById(id);
	}

	@Override
	public Postulant editPostulant(Postulant postulant) {
		// TODO Auto-generated method stub
		return postulantRepository.save(postulant);
	}

	@Override
	public Postulant getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkPostulant(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Postulant findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
