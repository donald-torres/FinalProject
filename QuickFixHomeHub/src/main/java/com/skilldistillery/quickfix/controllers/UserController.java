package com.skilldistillery.quickfix.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.quickfix.entities.User;
import com.skilldistillery.quickfix.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "users/{id}")
	public User show(@PathVariable("id") Integer id, HttpServletResponse res, Principal principal) {
		User user = null;
		try {
			user = userService.show(principal.getName(), id);
			if (user == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@PutMapping(path = "users/{id}")
	public User show(@PathVariable("id") Integer id, @RequestBody User user, HttpServletResponse res,
			HttpServletRequest req, Principal principal) {
		User updateUser = null;
		try {
			updateUser = userService.update(principal.getName(), id, user);
			if (updateUser == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return updateUser;
	}

}
