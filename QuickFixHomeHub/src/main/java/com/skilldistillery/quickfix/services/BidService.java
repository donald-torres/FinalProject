package com.skilldistillery.quickfix.services;

import java.util.List;

import com.skilldistillery.quickfix.entities.Bid;

public interface BidService {

	public List<Bid> index(int postId, String username);

	public Bid show(int postId, String username, int bidId);

	public Bid create(int jobPostId, Bid bid, String username);

	public Bid update(String username, int jobPostId, Bid bid, int bidId);

	public boolean destroy(String username, int jobPostId, int bidId);

}
