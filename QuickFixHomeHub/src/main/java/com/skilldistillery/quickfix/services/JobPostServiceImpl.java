package com.skilldistillery.quickfix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.quickfix.entities.JobPost;
import com.skilldistillery.quickfix.entities.User;
import com.skilldistillery.quickfix.repositories.JobPostRepository;
import com.skilldistillery.quickfix.repositories.UserRepository;

@Service
public class JobPostServiceImpl implements JobPostService {
	
	@Autowired
	private JobPostRepository jpRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<JobPost> index(String username) {
		return jpRepo.findByUser_Username(username);
	}

	@Override
	public JobPost show(String username, int id) {
		return jpRepo.findByUser_UsernameAndId(username, id);
	}

	@Override
	public JobPost create(String username, JobPost jobPost) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			jobPost.setUser(user);
			JobPost jp = jpRepo.saveAndFlush(jobPost);
			return jp;
		}
		return null;
	}

	@Override
	public JobPost update(String username, int id, JobPost jobPost) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean destroy(String username, int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<JobPost> getAllJobPostsWithProjectAreas() {
		return jpRepo.findAllWithProjectAreas();
	}

	@Override
	public JobPost getJobPostWithProjectAreas(int id) {
		return jpRepo.findByIdWithProjectAreas(id).orElse(null);
	}

}
