package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entite.Collaborateur;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Integer> {
	List <Collaborateur> findByDept_id(int id);

	List<Collaborateur> findByMatricule(String matricule);
}