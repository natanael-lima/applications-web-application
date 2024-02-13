package com.application.psm.controller;

import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.application.psm.model.JobOffer;
import com.application.psm.model.Postulant;
import com.application.psm.model.User;
import com.application.psm.service.JobOfferService;
import com.application.psm.service.PostulantService;
import com.application.psm.service.UserService;

@Controller
@RequestMapping("/postulant")
public class PostulantController {
	@Autowired 
	private JobOfferService jobService;
	@Autowired 
	private PostulantService postulantService;
	@Autowired 
	private UserService userService;

	@GetMapping("/new-postulant")
	public String showPostulantForm(Model model, @AuthenticationPrincipal UserDetails userDetails, @RequestParam("jobId") Long jobId) {
	    boolean isLoggedIn = userDetails != null;
	 // Obtener el Job seleccionado
	 	JobOffer job = jobService.getJobOfferById(jobId);
	 	System.out.println("JobId recibido: " + jobId); // Agrega esta línea para imprimir el jobId en la consola
	    Postulant postulant = new Postulant();
	    model.addAttribute("job", job);
	    model.addAttribute("postulante", postulant);
	    model.addAttribute("isLoggedIn", isLoggedIn);
		return "form-postulant";
	}
	
	@PostMapping("/registration-postulant")
	public String savePostulant(@ModelAttribute("postulante") Postulant postulant, @RequestParam("jobId") Long jobId, @RequestParam("pdfFile") MultipartFile pdfFile, @AuthenticationPrincipal UserDetails userDetails) {
		// Manejar el archivo PDF
        if (!pdfFile.isEmpty()) {
            try {
                // Obtener los bytes del archivo PDF
                byte[] pdfBytes = pdfFile.getBytes();
                // Guardar el contenido del archivo PDF en el objeto Postulant
                postulant.setCurriculum(pdfBytes);
                // Establecer el nombre del archivo PDF en el objeto Postulant si lo necesitas
                postulant.setPdfFileName(pdfFile.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar el error, por ejemplo, redirigiendo a una página de error
                return "layout/error/403";
            }
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Comprobamos si el usuario está autenticado y es UserDetails
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            // Si es UserDetails, obtenemos el nombre de usuario
            UserDetails loggedInUserDetails = (UserDetails) authentication.getPrincipal();
            
            // Ahora puedes obtener el usuario de tu servicio utilizando el nombre de usuario
            User user = userService.findByUsername(loggedInUserDetails.getUsername());
            
            // Asignamos el usuario al postulante
            postulant.setUser(user);
        }
        //jobId es el valor que eligue el usuario en form tiene que tener campo id="jobId" etc
        // Obtener el Job seleccionado
        JobOffer job = jobService.getJobOfferById(jobId);
        // Establecer el Job en la Postulante
        postulant.setJobOffer(job);
        // Guardar la JobOffer
        
        postulantService.savePostulant(postulant);
        return "redirect:/home";
	}
	
	
	//@Secured({"ROLE_USER","ROLE_ADMIN"})
	@Secured("hasRole('ADMIN')")
	@GetMapping("/admin")
	public String postulantShowTable(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails != null) {
            // Verificar si el usuario tiene el rol de administrador
            boolean isAdmin = userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
            
            if (isAdmin) {
                String username = userDetails.getUsername();
                model.addAttribute("username", username);
                User user = userService.findByUsername(username);
                model.addAttribute("user", user);
                model.addAttribute("postulants", postulantService.getAllPostulants());
                boolean isLoggedIn = userDetails != null;
                model.addAttribute("isLoggedIn", isLoggedIn);
                return "admin-postulants";
            } else {
                // Si el usuario no es administrador, redireccionar a una página de error 404
                return "/layout/error/403";
            }
        } else {
            // Manejar el caso cuando el usuario no está autenticado
        	 return "redirect:/user/login-user";
        }
	}

	
	@Secured("hasRole('VISITANTE')")
	@GetMapping("/user")
	public String mypostulantsShow(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails != null) {
            // Verificar si el usuario tiene el rol de visitante
            boolean isAdmin = userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_VISITANTE"));
            
            if (isAdmin) {
            	// Lógica para determinar si el usuario está logueado
        	    boolean isLoggedIn = userDetails != null;
        	    model.addAttribute("isLoggedIn", isLoggedIn);
        	  
        	    String username = userDetails.getUsername();
        	    User user = userService.findByUsername(username);
        	    
        	    
                Long userId = user.getId();
                List<Postulant> postulants = postulantService.getAllPostulantsByUserId(userId);
                model.addAttribute("postulants", postulants);
        	    
                return "user-postulants";
            } else {
                // Si el usuario es administrador, redireccionar a una página de error 404
                return "/layout/error/403";
            }
        } else {
            // Manejar el caso cuando el usuario no está autenticado
            return "redirect:/user/login-user";
        }
	}
	

    @GetMapping("/download/{postulantId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long postulantId) {
        // Obtener el postulante de la base de datos
        Postulant postulant = postulantService.getPostulantById(postulantId);
        if (postulant == null || postulant.getCurriculum() == null) {
            return ResponseEntity.notFound().build();
        }

        // Configurar los encabezados de la respuesta HTTP
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/pdf"); // Crear un MediaType para application/pdf
        headers.setContentType(mediaType); // Establecer el tipo de contenido
        headers.setContentDispositionFormData("filename", "curriculum.pdf");


        // Crear un recurso ByteArrayResource desde el contenido del PDF
        ByteArrayResource resource = new ByteArrayResource(postulant.getCurriculum());

        // Devolver el archivo PDF como una respuesta
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(postulant.getCurriculum().length)
                .body(resource);
    }
    
    @PostMapping("/{postulantId}/accept")
    public String acceptPostulant(@PathVariable("postulantId") Long postulantId) {
        // Lógica para aceptar la postulación
        Postulant postulant = postulantService.getPostulantById(postulantId);
        postulant.setEstado("aceptado");
        postulantService.savePostulant(postulant);
        return "redirect:/postulant/admin";
    }

    @PostMapping("/{postulantId}/reject")
    public String rejectPostulant(@PathVariable("postulantId") Long postulantId) {
        // Lógica para rechazar la postulación
        Postulant postulant = postulantService.getPostulantById(postulantId);
        postulant.setEstado("rechazado");
        postulantService.savePostulant(postulant);
        return "redirect:/postulant/admin";
    }
    
    
}
    