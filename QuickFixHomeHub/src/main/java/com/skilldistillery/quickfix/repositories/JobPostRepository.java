package com.skilldistillery.quickfix.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skilldistillery.quickfix.entities.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

	List<JobPost> findByUser_Username(String username);

	JobPost findByUser_UsernameAndId(String username, int id);

	JobPost searchById(int id);

	JobPost searchByUser_Username(String username);

	List<JobPost> findByTitleContainingIgnoreCase(String title);

	// Custom query to fetch job posts with project areas
	@Query("SELECT jp FROM JobPost jp LEFT JOIN FETCH jp.projectAreas")
	List<JobPost> findAllWithProjectAreas();

	// Fetch a single job post with project areas by ID
	@Query("SELECT jp FROM JobPost jp LEFT JOIN FETCH jp.projectAreas WHERE jp.id = :id")
	Optional<JobPost> findByIdWithProjectAreas(int id);

	List<JobPost> findByUser_UsernameAndCompleteFalse(String username);

	boolean existsByEnabledAndId(JobPost jobPost, int id);

}
