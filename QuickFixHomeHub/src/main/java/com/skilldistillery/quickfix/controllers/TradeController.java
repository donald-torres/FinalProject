package com.skilldistillery.quickfix.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.quickfix.entities.Trade;
import com.skilldistillery.quickfix.services.JobPostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class TradeController {
	
	@Autowired
	private JobPostService jpService;

	@GetMapping(path = { "trades", "trades/" })
	public List<Trade> index(HttpServletRequest req, HttpServletResponse res) {
		List<Trade> trades = jpService.indexTrade();
		if (trades == null) {
			res.setStatus(404);
		}
		return trades;
	}
	
	
}
