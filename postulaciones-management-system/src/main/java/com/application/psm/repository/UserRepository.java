package com.application.psm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.psm.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	User findByUsername(String username);
	
	boolean existsByUsername(String username);
}
