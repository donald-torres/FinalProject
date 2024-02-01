package com.skilldistillery.quickfix.services;

import java.util.List;

import com.skilldistillery.quickfix.entities.JobPost;
import com.skilldistillery.quickfix.entities.ProjectArea;
import com.skilldistillery.quickfix.entities.Specialty;
import com.skilldistillery.quickfix.entities.Trade;

public interface JobPostService {

	public List<JobPost> index(String username);

	public List<JobPost> indexNotComplete();

	public JobPost show(String username, int id);

	public JobPost create(String username, JobPost jobPost);

	public List<JobPost> getAllJobPostsWithProjectAreas();

	public JobPost getJobPostWithProjectAreas(int id);

	public JobPost update(String username, int id, JobPost jobPost);

	public JobPost showOne(String username);

	public boolean destroy(String username, int id);

	List<Trade> indexTrade();

	List<ProjectArea> indexProject();

	List<Specialty> indexSpecialty();

	public boolean addProjectAreatoPost(String username, int postId, int areaId);

	public boolean removeProjectAreatoPost(String username, int postId, int areaId);

	public boolean addTradetoPost(String username, int postId, int tradeId);

	public boolean removeTradetoPost(String username, int postId, int tradeId);

	public boolean addSpecialtytoPost(String username, int postId, int specId);

	public boolean removeSpecialtytoPost(String username, int postId, int specId);
}
