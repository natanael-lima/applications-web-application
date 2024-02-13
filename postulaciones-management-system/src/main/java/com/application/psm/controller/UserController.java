package com.application.psm.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.psm.model.User;
import com.application.psm.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	
	
	public UserController(UserService userService) {
	     this.userService = userService;
	}


	@GetMapping("/registration")
	public String showUserForm(Model model) {
		model.addAttribute("user", new User()); // Asegúrate de que el objeto 'user' esté en el modelo
		return "registration";
	}
	
	@PostMapping("/registration")
	public String saveUserAccount(@ModelAttribute("user") User user, HttpSession session) {
		
		boolean f = userService.checkUsername(user.getUsername());
		// System.out.println(user);
		if(f) {
			session.setAttribute("error","Username already exist");
		}else {
			
			User u = userService.saveUser(user);
			
			if (u != null) {
				// System.out.println("save sucess");
				session.setAttribute("success", "Register successfully");

			} else {
				// System.out.println("error in server");
				session.setAttribute("error", "Something wrong server");
			}
		}
		return "redirect:/registration?success";
	}
	
	
	@GetMapping("/profile")
	public String profileShow(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		// Lógica para determinar si el usuario está logueado
	    boolean isLoggedIn = userDetails != null;

	    // Puedes agregar más lógica si es necesario
	    model.addAttribute("isLoggedIn", isLoggedIn);

	    // Obtén el nombre del usuario directamente desde userDetails
	    if (isLoggedIn) {
	        String username = userDetails.getUsername();
	        model.addAttribute("username", username);
	        System.out.println("datos---------------------:" + username);
	        
	        // También agrega el objeto user al modelo si es necesario
	        User user = userService.findByUsername(username);
	        model.addAttribute("user", user);
	    }
		return "profile";
	}

    
}
