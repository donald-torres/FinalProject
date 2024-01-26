package com.skilldistillery.quickfix.services;

import com.skilldistillery.quickfix.entities.User;

public interface UserService {
	
	public User show(String username);
	public User create(String username, User user);
	public User update(String username, int id, User user);
	public boolean destroy(String username, int id);

}
