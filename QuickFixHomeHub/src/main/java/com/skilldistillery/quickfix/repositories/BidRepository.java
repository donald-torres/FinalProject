package com.skilldistillery.quickfix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.quickfix.entities.Bid;

public interface BidRepository extends JpaRepository<Bid, Integer> {

	List<Bid> searchByJobPost_Id(int id);

	List<Bid> findByEnabled(boolean enabled);

	Bid findByJobPost_Id(int id);
	
	Bid findByIdAndJobPost_Id(int  id, int jobPostId);
	
	 Bid findByIdAndJobPost_IdAndProvider_User_Username(int id, int jobPostId, String username);

}
