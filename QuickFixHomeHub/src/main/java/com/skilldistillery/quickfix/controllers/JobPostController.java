package com.skilldistillery.quickfix.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.quickfix.entities.JobPost;
import com.skilldistillery.quickfix.services.JobPostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class JobPostController {

	@Autowired
	private JobPostService jobPostService;

	@GetMapping(path = { "jobPosts", "jobPosts/" })
	public List<JobPost> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		List<JobPost> jobPosts = jobPostService.index(principal.getName());
		if (jobPosts == null) {
			res.setStatus(404);
		}
		return jobPosts;
	}

	@GetMapping(path = "jobPosts/{id}")
	public JobPost show(HttpServletRequest req, HttpServletResponse res, @PathVariable("id") int id, Principal principal) {
		JobPost jobPost = jobPostService.show(principal.getName(), id);
		if (jobPost == null) {
			res.setStatus(404);
		}
		return jobPost;
	}

	@PostMapping(path = "jobPosts")
	public JobPost create(HttpServletRequest req, HttpServletResponse res, @RequestBody JobPost jobPost, Principal principal) {
		JobPost newJobPost;
		try {
			System.out.println("********************************************** " + jobPost);
			newJobPost = jobPostService.create(principal.getName(), jobPost);
			res.setStatus(201);
			res.setHeader("Location", "http://localhost:8090/api/jobPosts/" + newJobPost.getId());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			newJobPost = null;
		}

		return newJobPost;
	}

}
