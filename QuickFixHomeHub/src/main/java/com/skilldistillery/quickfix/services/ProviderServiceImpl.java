package com.skilldistillery.quickfix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.quickfix.entities.Provider;
import com.skilldistillery.quickfix.repositories.ProviderRepository;

@Service
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
		updated.setCompany(provider.getCompany());;
		updated.setEmail(provider.getEmail());
		updated.setPhone(provider.getPhone());
		updated.setRatePerHour(provider.getRatePerHour());
		updated.setDescription(provider.getDescription());
		updated.setLogoUrl(provider.getLogoUrl());
		updated.setAddress(provider.getAddress());
		proRepo.save(updated);
		return updated;
	}

	@Override
	public boolean destroy(String username, int id) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public Provider index(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
