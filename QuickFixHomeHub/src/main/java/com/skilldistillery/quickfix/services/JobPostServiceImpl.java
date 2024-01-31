package com.skilldistillery.quickfix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.quickfix.entities.JobPost;
import com.skilldistillery.quickfix.entities.ProjectArea;
import com.skilldistillery.quickfix.entities.Specialty;
import com.skilldistillery.quickfix.entities.Trade;
import com.skilldistillery.quickfix.entities.User;
import com.skilldistillery.quickfix.repositories.JobPostRepository;
import com.skilldistillery.quickfix.repositories.ProjectAreaRepository;
import com.skilldistillery.quickfix.repositories.SpecialtyRepository;
import com.skilldistillery.quickfix.repositories.TradeRepository;
import com.skilldistillery.quickfix.repositories.UserRepository;

@Service
public class JobPostServiceImpl implements JobPostService {

	@Autowired
	private JobPostRepository jpRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TradeRepository tradeRepo;
	@Autowired
	private ProjectAreaRepository paRepo;
	@Autowired
	private SpecialtyRepository specRepo;

	@Override
	public List<JobPost> index(String username) {
		return jpRepo.findByUser_Username(username);
	}
	@Override
	public List<Trade> indexTrade() {
		return tradeRepo.findAll();
	}
	@Override
	public List<ProjectArea> indexProject() {
		return paRepo.findAll();
	}
	@Override
	public List<Specialty> indexSpecialty() {
		return specRepo.findAll();
	}
	

	@Override
	public List<JobPost> indexNotComplete(String username) {
		return jpRepo.findByUser_UsernameAndCompleteFalse(username);
	}

	@Override
	public JobPost show(String username, int id) {
		return jpRepo.findByUser_UsernameAndId(username, id);
	}

	@Override
	public JobPost create(String username, JobPost jobPost) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			jobPost.setUser(user);
			JobPost jp = jpRepo.saveAndFlush(jobPost);
			return jp;
		}
		return null;
	}

	@Override
	public JobPost update(String username, int id, JobPost jobPost) {
		JobPost updated = jpRepo.findByUser_UsernameAndId(username, id);
		updated.setTitle(jobPost.getTitle());
		updated.setDescription(jobPost.getDescription());
		updated.setComplete(jobPost.getComplete());
		updated.setStartDate(jobPost.getStartDate());
		updated.setSpecialInstructions(jobPost.getSpecialInstructions());
		updated.setMaterialsProvided(jobPost.getMaterialsProvided());
		updated.setImageUrl(jobPost.getImageUrl());
		updated.setBudgetMax(jobPost.getBudgetMax());
		updated.setBidBy(jobPost.getBidBy());
		updated.setProjectAreas(jobPost.getProjectAreas());
		updated.setAppointments(jobPost.getAppointments());
		updated.setTrades(jobPost.getTrades());
		updated.setSpecialties(jobPost.getSpecialties());
		updated.setBids(jobPost.getBids());
		updated.setEnabled(jobPost.getEnabled());
		jpRepo.save(updated);
		return updated;
	}

	@Override
	public boolean destroy(String username, int id, JobPost jobPost) {
			JobPost toDelete = jpRepo.findByUser_UsernameAndId(username, id);
			if (toDelete != null) {
				toDelete.setEnabled(false);
				jpRepo.save(toDelete);
			}
			return toDelete.getEnabled();

	}

}
