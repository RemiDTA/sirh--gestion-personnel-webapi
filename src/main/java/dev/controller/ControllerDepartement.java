package dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.entite.Departement;
import dev.repository.DepartementRepository;

@RestController
@RequestMapping("/api/departements")
public class ControllerDepartement {

	@Autowired
	private DepartementRepository departementRepo;

	@GetMapping
	public List<Departement> listerDepartement() {
		return this.departementRepo.findAll();
	}

}
