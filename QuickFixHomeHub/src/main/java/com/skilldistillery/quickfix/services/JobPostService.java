package com.skilldistillery.quickfix.services;

import java.util.List;

import com.skilldistillery.quickfix.entities.JobPost;
import com.skilldistillery.quickfix.entities.ProjectArea;
import com.skilldistillery.quickfix.entities.Specialty;
import com.skilldistillery.quickfix.entities.Trade;

public interface JobPostService {

	public List<JobPost> index(String username);

	public List<JobPost> indexNotComplete(String username);

	public JobPost show(String username, int id);

	public JobPost create(String username, JobPost jobPost);

	public JobPost update(String username, int id, JobPost jobPost);
	    
    public JobPost showOne(String username);

	public boolean destroy(String username, int id, JobPost jobPost);

	List<Trade> indexTrade();

	List<ProjectArea> indexProject();

	List<Specialty> indexSpecialty();
}
