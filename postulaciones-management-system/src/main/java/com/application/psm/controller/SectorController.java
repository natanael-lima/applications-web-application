package com.application.psm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.application.psm.model.Sector;
import com.application.psm.service.SectorService;

@Controller
@RequestMapping("/sector")
public class SectorController {

	@Autowired
	private SectorService sectorService;
	
	@GetMapping("/form-sector")
	public String sectorShowAll(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		// Lógica para determinar si el usuario está logueado
	    boolean isLoggedIn = userDetails != null;

	    // Puedes agregar más lógica si es necesario
	    model.addAttribute("sectors", sectorService.getAllSectors()); // Asegúrate de que el objeto 'user' esté en el modelo
	    model.addAttribute("isLoggedIn", isLoggedIn);
		return "form-sector";
	}
	@GetMapping("/nuevo")
	public String sectorShowForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
	    boolean isLoggedIn = userDetails != null;
	    Sector sector = new Sector();
	    model.addAttribute("sector", sector);
	    model.addAttribute("isLoggedIn", isLoggedIn);
	    return "sector";
	}

	
	@PostMapping("/registration")
	public String saveSector(@ModelAttribute("sector") Sector sector) {

		sectorService.saveSector(sector);
		return "redirect:/sector/form-sector";
	}
}
