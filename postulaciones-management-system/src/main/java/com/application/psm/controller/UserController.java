package com.application.psm.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/registration")
public class UserController {
	
	private UserService userService;
	
	
	public UserController(UserService userService) {
	     this.userService = userService;
	}


	@GetMapping
	public String showUserForm(Model model) {
		model.addAttribute("user", new User()); // Asegúrate de que el objeto 'user' esté en el modelo
		return "registration";
	}
	
	@PostMapping
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
	

}
