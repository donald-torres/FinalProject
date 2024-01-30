package com.skilldistillery.quickfix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.quickfix.entities.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {
	
	List<JobPost> findByUser_Username(String username);
	
	JobPost findByUser_UsernameAndId(String username,int id);

	List<JobPost> findByTitleContainingIgnoreCase(String title);

}
