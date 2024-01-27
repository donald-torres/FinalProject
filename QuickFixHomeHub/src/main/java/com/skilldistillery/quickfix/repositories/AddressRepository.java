package com.skilldistillery.quickfix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.quickfix.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	

}
