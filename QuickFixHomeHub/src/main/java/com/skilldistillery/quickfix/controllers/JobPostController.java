package com.skilldistillery.quickfix.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@GetMapping(path = { "activeJobPosts", "activeJobPosts/" })
	public List<JobPost> indexNotCompleted(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		List<JobPost> jobPosts = jobPostService.indexNotComplete(principal.getName());
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

	@PutMapping(path = "jobPosts/{id}")
	public JobPost update(@PathVariable("id") Integer id, @RequestBody JobPost jobPost, HttpServletResponse res,
			HttpServletRequest req, Principal principal) {
		JobPost updatePost = null;
		try {
			updatePost = jobPostService.update(principal.getName(), id, jobPost);
			if (updatePost == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return updatePost;
	}
	
	@DeleteMapping(path = "jobPosts/{id}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable("id") int id, @RequestBody JobPost jobPost, Principal principal) {
		JobPost toDelete = jobPostService.show(principal.getName(), id);
		try {
			if (toDelete.getEnabled() == true) {
					jobPostService.destroy(principal.getName(), id, jobPost);
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}


	
}
