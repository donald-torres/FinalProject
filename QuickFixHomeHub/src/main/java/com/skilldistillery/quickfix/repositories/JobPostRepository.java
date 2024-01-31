package com.skilldistillery.quickfix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.quickfix.entities.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {
	
	List<JobPost> findByUser_Username(String username);
	
	JobPost findByUser_UsernameAndId(String username,int id);
	
	JobPost searchById(int id);
	
	JobPost searchByUser_Username(String username);

	List<JobPost> findByTitleContainingIgnoreCase(String title);
	
	List<JobPost> findByUser_UsernameAndCompleteFalse(String username);

	boolean existsByEnabledAndId(JobPost jobPost, int id);
	

}
