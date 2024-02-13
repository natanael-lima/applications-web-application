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
import org.springframework.web.bind.annotation.RequestParam;

import com.application.psm.model.JobOffer;
import com.application.psm.model.Sector;
import com.application.psm.service.JobOfferService;
import com.application.psm.service.SectorService;

@Controller
@RequestMapping("/job")
public class JobOfferController {
	@Autowired
	private JobOfferService jobService;
	
	@Autowired
	private SectorService sectorService;
	
	@GetMapping("/all-jobs")
	public String jobOfferForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		// Lógica para determinar si el usuario está logueado
	    boolean isLoggedIn = userDetails != null;
	    // Puedes agregar más lógica si es necesario
	    model.addAttribute("isLoggedIn", isLoggedIn);
	    model.addAttribute("sectors", sectorService.getAllSectors());
	    model.addAttribute("jobs", jobService.getAllJobOffer()); // Asegúrate de que el objeto 'user' esté en el modelo
		model.addAttribute("userDetails", userDetails); // Asegúrate de que el objeto 'userDetails' esté en el modelo
		return "publishing-job";
	}
	
	@GetMapping("/new-job")
	public String jobShowForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
	    boolean isLoggedIn = userDetails != null;
	    JobOffer job = new JobOffer();
	    model.addAttribute("job", job);
	    model.addAttribute("sectors", sectorService.getAllSectors());
	    model.addAttribute("isLoggedIn", isLoggedIn);
	    return "form-job";
	}

	
	@PostMapping("/registration-job")
	public String saveJobOffer(@ModelAttribute("sector") JobOffer job, @RequestParam("sectorId") Long sectorId) {
		//sectorId es el valor que eligue el usuario en form tiene que tener campo id="sectorId" etc
		 // Obtener el Sector seleccionado
	    Sector sector = sectorService.getSectorById(sectorId);
	    // Establecer el Sector en la JobOffer
	    job.setSector(sector);
	    // Guardar la JobOffer
		jobService.saveJobOffer(job);
		return "redirect:/job//all-jobs";
	}
}
