package com.skilldistillery.quickfix.services;

import com.skilldistillery.quickfix.entities.User;

public interface AuthService {
	
	public User register(User user);
	public User getUserByUsername(String username);
	

}
