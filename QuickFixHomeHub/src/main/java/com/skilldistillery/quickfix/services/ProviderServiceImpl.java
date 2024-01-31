package com.skilldistillery.quickfix.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.quickfix.entities.Provider;
import com.skilldistillery.quickfix.repositories.ProviderRepository;

public class ProviderServiceImpl implements ProviderService {
	
	@Autowired
	private ProviderRepository proRepo;

	@Override
	public Provider show(int id, String username) {
		return proRepo.searchByIdAndUser_Username(id, username);
	}

	@Override
	public Provider create(String username, Provider provider) {
	
		return proRepo.saveAndFlush(null);
	}

	@Override
	public Provider update(String username, int id, Provider provider) {
		Provider updated = proRepo.findById(id);
//		updated.setFirstName(user.getFirstName());
//		updated.setLastName(user.getLastName());
//		updated.setEmail(user.getEmail());
//		updated.setPhone(user.getPhone());
//		updated.setBiography(user.getBiography());
//		updated.setImageUrl(user.getImageUrl());
//		updated.setAddress(user.getAddress());
//		userRepo.save(updated);
		return updated;
	}

	@Override
	public boolean destroy(String username, int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
