package com.skilldistillery.quickfix.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.quickfix.entities.ProjectArea;
import com.skilldistillery.quickfix.services.JobPostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class ProjectAreaController {
	
	@Autowired
	private JobPostService jpService;

	@GetMapping(path = { "projectAreas", "projectAreas/" })
	public List<ProjectArea> index(HttpServletRequest req, HttpServletResponse res) {
		List<ProjectArea> areas = jpService.indexProject();
		if (areas == null) {
			res.setStatus(404);
		}
		return areas;
	}
	
	
}
