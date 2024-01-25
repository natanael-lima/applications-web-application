package com.application.psm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.application.psm.model.User;
import com.application.psm.repository.UserRepository;
import com.application.psm.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	private UserRepository repo;
	@Autowired
	private UserService userService;
	
	/*
	@GetMapping("/login")
	public String login() {
		return "login";
	}*/
	
	
	@GetMapping("/")
	public String showHome(Model model, @AuthenticationPrincipal UserDetails userDetails) {
	    // Lógica para determinar si el usuario está logueado
	    boolean isLoggedIn = userDetails != null;

	    // Puedes agregar más lógica si es necesario

	    model.addAttribute("isLoggedIn", isLoggedIn);

	    return "index";
	}

	
	 @GetMapping("/login")
	 public String login(Model model) {
	      // Lógica para mostrar la página de inicio de sesión
	      User user = new User();
	      user.setLoggedIn(false);
	      model.addAttribute("user", user);

	      return "login";
	    }

	 @PostMapping("/login")
	 public String loginSubmit(@ModelAttribute User user) {
	     // Lógica para verificar las credenciales y establecer el estado de inicio de sesión
	     user.setLoggedIn(true);

	     // Redirige a la página principal o a la página específica para usuarios logueados
	     return "redirect:/";
	    }
	 
	 @GetMapping("/logout")
		public String logout(@ModelAttribute User user) {
		       // Lógica para cerrar sesión y cambiar el estado de loggedIn
		        user.setLoggedIn(false);
		      return "redirect:/login";
		}
	
	 
	@GetMapping("/form-job-offer")
	public String offer(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		// Lógica para determinar si el usuario está logueado
	    boolean isLoggedIn = userDetails != null;

	    // Puedes agregar más lógica si es necesario

	    model.addAttribute("isLoggedIn", isLoggedIn);
		return "form-job-offer";
	}
	
	@GetMapping("/form-postulant")
	public String postulant(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		// Lógica para determinar si el usuario está logueado
	    boolean isLoggedIn = userDetails != null;

	    // Puedes agregar más lógica si es necesario

	    model.addAttribute("isLoggedIn", isLoggedIn);
		return "form-postulant";
	}
	
	@GetMapping("/form-sector")
	public String sector(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		// Lógica para determinar si el usuario está logueado
	    boolean isLoggedIn = userDetails != null;

	    // Puedes agregar más lógica si es necesario

	    model.addAttribute("isLoggedIn", isLoggedIn);
		return "form-sector";
	}
	
	@GetMapping("/postulaciones")
	public String myPostulaciones(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		// Lógica para determinar si el usuario está logueado
	    boolean isLoggedIn = userDetails != null;

	    // Puedes agregar más lógica si es necesario

	    model.addAttribute("isLoggedIn", isLoggedIn);
		return "my-postulations";
	}
	
	
	@GetMapping("/user/profile")
	public String profile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		// Lógica para determinar si el usuario está logueado
	    boolean isLoggedIn = userDetails != null;

	    // Puedes agregar más lógica si es necesario
	    System.out.println("entre---------------:");
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
