package com.skilldistillery.quickfix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.quickfix.entities.Specialty;
import com.skilldistillery.quickfix.entities.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer>{

	Trade searchById(int id);
	
}
