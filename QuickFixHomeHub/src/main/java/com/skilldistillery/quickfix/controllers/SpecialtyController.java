package com.skilldistillery.quickfix.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.quickfix.entities.Specialty;
import com.skilldistillery.quickfix.services.JobPostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class SpecialtyController {
	
	@Autowired
	private JobPostService jpService;

	@GetMapping(path = { "specialties", "specialties/" })
	public List<Specialty> index(HttpServletRequest req, HttpServletResponse res) {
		List<Specialty> specs = jpService.indexSpecialty();
		if (specs == null) {
			res.setStatus(404);
		}
		return specs;
	}
	
	
}
