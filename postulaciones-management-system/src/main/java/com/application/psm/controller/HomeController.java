package com.application.psm.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.application.psm.model.User;
import com.application.psm.repository.UserRepository;
import com.application.psm.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {


	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/form-job-offer")
	public String offer() {
		return "form-job-offer";
	}
	
	@GetMapping("/form-postulant")
	public String postulant() {
		return "form-postulant";
	}

	

}	
