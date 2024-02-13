package com.application.psm.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.psm.model.Role;
import com.application.psm.model.User;
import com.application.psm.repository.RoleRepository;
import com.application.psm.repository.UserRepository;
import com.application.psm.service.UserService;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	@Override
	public User saveUser(User user) {

				
		Role userRole = roleRepository.findById(1L).get();

		User newUser = new User(
			    user.getName(),
			    user.getLastname(),
			    user.getEmail(),
			    user.getUsername(),
			    passwordEncoder.encode(user.getPassword()),
			    userRole
			);
		newUser.setLoggedIn(false);
		return userRepository.save(newUser);
	}


	@Override
	public boolean checkUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.existsByUsername(username);
	}


	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}


	@Override
	public String getUserRole(String username) {
		User user = userRepository.findByUsername(username);
        if (user != null) {
            Long roleId = user.getRoles().getId();
            Role role = roleRepository.findById(roleId).orElse(null);
            if (role != null) {
                return role.getRoleName();
            }
        }
        return null;
	}
	
	
	
}

	/*@Override
	public User saveUser(User user) {
		User newuser = new User(user.getFirstName(), 
				user.getLastName(), user.getEmail(),
				passwordEncoder.encode(user.getPassword()), Arrays.asList(new Role("ROLE_USER")));
		
		return userRepository.save(user);
	}
	
	

	}*/

	
	