package com.skilldistillery.quickfix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.quickfix.entities.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {
	

}
