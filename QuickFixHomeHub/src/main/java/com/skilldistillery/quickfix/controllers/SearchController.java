package com.skilldistillery.quickfix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.quickfix.entities.JobPost;
import com.skilldistillery.quickfix.entities.Provider;
import com.skilldistillery.quickfix.entities.User;
import com.skilldistillery.quickfix.services.SearchService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class SearchController {

	@Autowired
	private SearchService searchService;

	// Search for Providers by company
	@GetMapping(path = "search/providers/{company}")
	public List<Provider> searchProviders(@PathVariable("company") String company, HttpServletResponse res) {
		List<Provider> providers = null;
		try {
			providers = searchService.searchProvidersByCompany(company);
			if (providers == null || providers.isEmpty()) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return providers;
	}

	// Search for Users by username
	@GetMapping(path = "search/users/{username}")
	public List<User> searchUsers(@PathVariable("username") String username, HttpServletResponse res) {
		List<User> users = null;
		try {
			users = searchService.searchUsersByUsername(username);
			if (users == null || users.isEmpty()) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return users;
	}

	// Search for JobPosts by title
	@GetMapping(path = "search/jobposts/{title}")
	public List<JobPost> searchJobPosts(@PathVariable("title") String title, HttpServletResponse res) {
		List<JobPost> jobPosts = null;
		try {
			jobPosts = searchService.searchJobPostsByTitle(title);
			if (jobPosts == null || jobPosts.isEmpty()) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return jobPosts;
	}

}
