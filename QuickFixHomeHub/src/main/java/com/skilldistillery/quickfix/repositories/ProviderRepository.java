package com.skilldistillery.quickfix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.quickfix.entities.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
	List<Provider> findByCompanyContainingIgnoreCase(String company);
}
