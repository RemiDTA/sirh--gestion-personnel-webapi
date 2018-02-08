package dev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Collaborateur;
import dev.repository.CollaborateurRepository;

@RestController
@RequestMapping("/api/collaborateurs")
public class ControllerCollaborateur {

	@Autowired
	private CollaborateurRepository collaborateurRepo;
	
	@GetMapping(value = "/{matricule}")
	public List<Collaborateur> listerCollaborateurParam(@PathVariable String matricule) {
		return this.collaborateurRepo.findByMatricule(matricule);
	}
	
	@GetMapping
	public List<Collaborateur> listerCollaborateur2(@RequestParam("departement") Optional<Integer> dpt) {
		if (dpt.isPresent())
		{
			return this.collaborateurRepo.findByDept_id(dpt.get());
		}
		else
		{
			return this.collaborateurRepo.findAll();
			
		}
		
	}

}
