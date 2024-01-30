package com.skilldistillery.quickfix.services;

import java.util.List;

import com.skilldistillery.quickfix.entities.JobPost;
import com.skilldistillery.quickfix.entities.Provider;
import com.skilldistillery.quickfix.entities.User;

public interface SearchService {
	
	List<Provider> searchProvidersByCompany(String company);
    List<User> searchUsersByUsername(String username);
    List<JobPost> searchJobPostsByTitle(String title);

}
