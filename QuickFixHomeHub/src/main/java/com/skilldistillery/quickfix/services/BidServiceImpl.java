package com.skilldistillery.quickfix.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.quickfix.entities.Bid;
import com.skilldistillery.quickfix.entities.JobPost;
import com.skilldistillery.quickfix.entities.Provider;
import com.skilldistillery.quickfix.repositories.BidRepository;
import com.skilldistillery.quickfix.repositories.JobPostRepository;
import com.skilldistillery.quickfix.repositories.ProviderRepository;

@Service
public class BidServiceImpl implements BidService {

	@Autowired
	private BidRepository bidRepo;

	@Autowired
	private JobPostRepository jobPostRepo;
	@Autowired
	private ProviderRepository providerRepo;

	@Override
	public List<Bid> index(int postId, String username) {
		JobPost jp = jobPostRepo.findByUser_UsernameAndId(username, postId);

		List<Bid> bids = jp.getBids();

		return bids;
	}

	@Override
	public Bid show(int jobPostId, String username, int bidId) {

		return bidRepo.findByIdAndJobPost_IdAndProvider_User_Username(bidId, jobPostId, username);
	}

	@Override
	public Bid create(int jobPostId, Bid bid, String username) {
		JobPost jp = jobPostRepo.searchById(jobPostId);
		if (jp != null && bid.getProvider() != null) {
			Provider p = providerRepo.searchByIdAndUser_Username(bid.getProvider().getId(), username);
			if (p == null) {
				return null;

			}
			bid.setProvider(p);
			bid.setJobPost(jp);
			bid.setAccepted(false);
			bid.setEnabled(true);
			bid.setBidDate(LocalDateTime.now());
			Bid newBid = bidRepo.saveAndFlush(bid);
			return newBid;
		}
		return null;
	}

	@Override
	public Bid update(String username, int jobPostId, Bid bid, int bidId) {
		JobPost jp = jobPostRepo.searchById(jobPostId);
		if (jp != null && bid.getProvider() != null) {
			Provider p = providerRepo.searchByIdAndUser_Username(bid.getProvider().getId(), username);

			Bid updatedBid = bidRepo.findByIdAndJobPost_Id(bidId, jobPostId);
			if (updatedBid != null) {
				updatedBid.setProvider(p);
				updatedBid.setJobPost(jp);
				updatedBid.setProviderNote(bid.getProviderNote());
				updatedBid.setProviderComment(bid.getProviderComment());;
				updatedBid.setRatingByProvider(bid.getRatingByProvider());;
				
				updatedBid.setAccepted(bid.getAccepted());;
				updatedBid.setAmount(bid.getAmount());
				updatedBid.setBidDate(LocalDateTime.now());
				bidRepo.saveAndFlush(updatedBid);
			}
			return updatedBid;

		}

		return null;
	}

	@Override
	public boolean destroy(String username, int jobPostId,int bid) {
		
		
		
		


		
		return false;
	}

}
