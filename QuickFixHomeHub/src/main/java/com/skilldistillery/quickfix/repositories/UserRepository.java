package com.skilldistillery.quickfix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.quickfix.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	User findByUsernameAndId(String username, int id);
	
	User findById(int id);
	List<User> findByUsernameContainingIgnoreCase(String username);

}
