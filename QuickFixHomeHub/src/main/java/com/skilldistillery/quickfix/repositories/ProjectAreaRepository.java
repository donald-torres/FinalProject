package com.skilldistillery.quickfix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.quickfix.entities.ProjectArea;

public interface ProjectAreaRepository extends JpaRepository<ProjectArea, Integer>{
	
	ProjectArea searchById(int id);

}
