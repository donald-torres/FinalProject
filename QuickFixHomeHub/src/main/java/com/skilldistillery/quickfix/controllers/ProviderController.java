package com.skilldistillery.quickfix.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.quickfix.entities.Provider;
import com.skilldistillery.quickfix.services.ProviderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class ProviderController {

	@Autowired
	private ProviderService providerService;

	@GetMapping(path = "providers/{id}")
	public Provider show(@PathVariable("id") Integer id, HttpServletResponse res, Principal principal) {
		Provider provider = null;
		try {
			provider = providerService.show(id, principal.getName());
			if (provider == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return provider;
	}
	@PostMapping(path = "providers")
	public Provider create(HttpServletRequest req, HttpServletResponse res, @RequestBody Provider provider,
			Principal principal) {
		Provider newProvider;
		try {
			newProvider = providerService.create(principal.getName(), provider);
			res.setStatus(201);
			res.setHeader("Location", "http://localhost:8090/api/providers/" + newProvider.getId());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			newProvider = null;
		}

		return newProvider;
	}

	@PutMapping(path = "providers/{id}")
	public Provider update(@PathVariable("id") Integer id, @RequestBody Provider provider, HttpServletResponse res,
			HttpServletRequest req, Principal principal) {
		Provider updateProvider = null;
		try {
			updateProvider = providerService.update(principal.getName(), id, provider);
			if (updateProvider == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return updateProvider;
	}

}
