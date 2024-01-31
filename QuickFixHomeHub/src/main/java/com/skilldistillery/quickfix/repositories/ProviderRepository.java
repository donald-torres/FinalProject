package com.skilldistillery.quickfix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.quickfix.entities.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
	List<Provider> findByCompanyContainingIgnoreCase(String company);
	Provider searchById(int id);
	Provider searchByIdAndUser_Username(int id,String username);
	Provider findById(int id);
}
