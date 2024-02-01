package com.skilldistillery.quickfix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.quickfix.entities.Specialty;

public interface SpecialtyRepository extends JpaRepository<Specialty, Integer>{

	Specialty searchById(int id);
	
}
