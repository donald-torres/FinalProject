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
	public User show(String username, int id) {
		return userRepo.findByUsernameAndId(username, id);
	}

	@Override
	public User create(String username, User user) {
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User update(String username, int id, User user) {
		User updated = userRepo.findById(id);
		updated.setFirstName(user.getFirstName());
		updated.setLastName(user.getLastName());
		updated.setEmail(user.getEmail());
		updated.setPhone(user.getPhone());
		updated.setBiography(user.getBiography());
		updated.setImageUrl(user.getImageUrl());
		updated.setAddress(user.getAddress());
		userRepo.save(updated);
		return updated;
	}

	@Override
	public boolean destroy(String username, int id) {
		boolean success = false;
		User deleted = userRepo.findById(id);
		if (deleted != null) {
			deleted.setEnabled(false);
			userRepo.save(deleted);
			success = true;
		}
		return success;
	}

}
