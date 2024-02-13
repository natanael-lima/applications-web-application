package com.application.psm.service;

import com.application.psm.model.User;


public interface UserService{
	
	//List<User> getAllUsers();
	
	public User saveUser(User user);
	
	public boolean checkUsername(String username);
	
	public User findByUsername (String username);
	
	public String getUserRole (String username);
	//void removeSessionMessage();
	
	//void deleteUser(Long id);
	
	//User editUser(User user);
	
	//User getUserById(Long id);
	
}
