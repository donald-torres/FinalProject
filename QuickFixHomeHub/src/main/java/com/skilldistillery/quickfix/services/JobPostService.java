package com.skilldistillery.quickfix.services;

import java.util.List;

import com.skilldistillery.quickfix.entities.JobPost;

public interface JobPostService {

	   public List<JobPost> index(String username);

	    public JobPost show(String username, int id);

	    public JobPost create(String username, JobPost jobPost);

	    public JobPost update(String username, int id, JobPost jobPost);

	    public boolean destroy(String username, int id);
	    
	    public List<JobPost> getAllJobPostsWithProjectAreas();
	    
	    public JobPost getJobPostWithProjectAreas(int id);
}
