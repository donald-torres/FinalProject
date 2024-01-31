package com.skilldistillery.quickfix.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.quickfix.entities.Bid;
import com.skilldistillery.quickfix.services.BidService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class BidController {

	@Autowired
	private BidService bidService;

	@GetMapping({ "jobPosts/{postId}/bids" })
	public List<Bid> getBids(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@PathVariable("postId") int postId) {

		List<Bid> bids = bidService.index(postId, principal.getName());

		return bids;

	}
	
	@GetMapping({ "jobPosts/{postId}/bids/{bidId}" })
	public Bid showBid(HttpServletRequest req, HttpServletResponse res, Principal principal,
			@PathVariable("postId") int postId,@PathVariable("bidId") int bidId) {
		
		Bid abid = bidService.show(postId,principal.getName(),bidId );
		
		if ( abid == null) {
			return null;
		}
		return abid;
		
	}

	@PostMapping({ "jobPosts/{postId}/bids" })
	public Bid addBid(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody Bid bid,
			@PathVariable("postId") int postId) {

		Bid newBid = null;
		try {
			newBid = bidService.create(postId, bid, principal.getName());
			if (newBid == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);

			e.printStackTrace();
		}

		return newBid;

	}

	@PutMapping({ "jobPosts/{postId}/bids/{bidId}" })
	public Bid updateBid(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody Bid bid,
			@PathVariable("postId") int postId, @PathVariable("bidId") int bidId) {

		Bid updatedBid = null;
		updatedBid = bidService.update(principal.getName(), postId, bid, bidId);

		return updatedBid;

	}

}
