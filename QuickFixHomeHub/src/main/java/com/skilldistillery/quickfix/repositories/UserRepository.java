package com.skilldistillery.quickfix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.quickfix.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);

}
