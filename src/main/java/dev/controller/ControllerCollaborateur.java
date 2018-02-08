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

import dev.entite.Banque;
import dev.entite.Collaborateur;
import dev.repository.BanqueRepository;
import dev.repository.CollaborateurRepository;

@RestController
@RequestMapping("/api/collaborateurs")
public class ControllerCollaborateur {

	@Autowired
	private CollaborateurRepository collaborateurRepo;
	@Autowired
	private BanqueRepository banqueRepo;
	
	@GetMapping(value = "/{matricule}")
	public Collaborateur listerCollaborateurParam(@PathVariable String matricule) {
		return this.collaborateurRepo.findByMatricule(matricule);
	}
	
	@GetMapping(value = "/{matricule}/banque")
	public List <Banque> listerBanqueCollaborateur(@PathVariable String matricule) {
		Collaborateur c=this.collaborateurRepo.findByMatricule(matricule);
		return c.getBanques();
				
	}
	
	@PutMapping(value = "/{matricule}/banque")
	public void modifierBanqueParam(@PathVariable String matricule, @RequestBody Banque bq) {
		Banque bqModif= this.banqueRepo.findOne(bq.getId()) ;
		bqModif.setNomBanque(bq.getNomBanque());
		this.banqueRepo.save(bqModif);
	}
	
	@PutMapping(value = "/{matricule}")
	public void modifierCollaborateurParam(@PathVariable String matricule, @RequestBody Collaborateur col) {
		Collaborateur colModif= this.collaborateurRepo.findByMatricule(matricule);
		colModif.setDept(col.getDept());
		colModif.setNom(col.getNom());
		colModif.setPrenom(col.getPrenom());
		this.collaborateurRepo.save(colModif);
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
