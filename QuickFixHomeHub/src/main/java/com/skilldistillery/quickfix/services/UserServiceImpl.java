package com.skilldistillery.quickfix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.quickfix.entities.User;
import com.skilldistillery.quickfix.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User show(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public User create(String username, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(String username, int id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean destroy(String username, int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
