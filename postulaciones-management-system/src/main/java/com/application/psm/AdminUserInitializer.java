package com.application.psm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.application.psm.model.Role;
import com.application.psm.model.User;
import com.application.psm.service.RoleService;
import com.application.psm.service.UserService;

@Component
public class AdminUserInitializer implements ApplicationRunner {
	@Autowired 
    private UserService userService;
	
	@Autowired 
    private RoleService roleService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		try {
            if (!roleService.existsByName("ADMIN")) {
                Role adminRole = new Role("ADMIN",null);
                roleService.saveRole(adminRole);
            }

            if (!roleService.existsByName("VISITANTE")) {
                Role userRole = new Role("VISITANTE",null);
                roleService.saveRole(userRole);
            }

            if (!userService.existsAdminRole()) {
                User adminUser = new User();
                adminUser.setName("Natanael");
                adminUser.setLastname("Lima");
                adminUser.setEmail("admin@empresa.com");
                adminUser.setUsername("admin");
                adminUser.setPassword("admin"); 
                userService.saveUser(adminUser);
            }
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir durante la inicialización
            e.printStackTrace();
        }
        
	}
}
