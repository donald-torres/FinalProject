package com.skilldistillery.quickfix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.quickfix.entities.JobPost;
import com.skilldistillery.quickfix.entities.Provider;
import com.skilldistillery.quickfix.entities.User;
import com.skilldistillery.quickfix.repositories.JobPostRepository;
import com.skilldistillery.quickfix.repositories.ProviderRepository;
import com.skilldistillery.quickfix.repositories.UserRepository;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobPostRepository jobPostRepository;

	@Override
	public List<Provider> searchProvidersByCompany(String company) {
		return providerRepository.findByCompanyContainingIgnoreCase(company);
	}

	@Override
	public List<User> searchUsersByUsername(String username) {
		return userRepository.findByUsernameContainingIgnoreCase(username);
	}

	@Override
	public List<JobPost> searchJobPostsByTitle(String title) {
		return jobPostRepository.findByTitleContainingIgnoreCase(title);
	}

}
