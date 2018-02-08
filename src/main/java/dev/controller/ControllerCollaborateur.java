package dev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public Collaborateur listerCollaborateurParam(@PathVariable String matricule) {
		return this.collaborateurRepo.findByMatricule(matricule);
	}
	
	@PutMapping(value = "/{matricule}")
	public void modifierCollaborateurParam(@PathVariable String matricule, @RequestBody Collaborateur col) {
		Collaborateur colModif= this.collaborateurRepo.findByMatricule(col.getMatricule());
		colModif.setDept(col.getDept());
		colModif.setNom(col.getNom());
		colModif.setPrenom(col.getPrenom());
		collaborateurRepo.save(colModif);
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
