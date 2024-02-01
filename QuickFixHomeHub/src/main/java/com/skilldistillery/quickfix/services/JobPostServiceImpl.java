package com.skilldistillery.quickfix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.quickfix.entities.JobPost;
import com.skilldistillery.quickfix.entities.ProjectArea;
import com.skilldistillery.quickfix.entities.Specialty;
import com.skilldistillery.quickfix.entities.Trade;
import com.skilldistillery.quickfix.entities.User;
import com.skilldistillery.quickfix.repositories.BidRepository;
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
	private BidRepository bidRepo;
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
	public JobPost showOne(String username) {
		return jpRepo.searchByUser_Username(username);
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
		updated.setEnabled(jobPost.getEnabled());
		jpRepo.save(updated);
		return updated;
	}

	@Override
	public boolean destroy(String username, int id) {
		JobPost toDelete = jpRepo.findByUser_UsernameAndId(username, id);
		if (toDelete != null) {
			toDelete.setEnabled(false);
			toDelete.setEnabled(true);
			jpRepo.save(toDelete);
		}
		return toDelete.getEnabled();

	}

	@Override
	public List<JobPost> getAllJobPostsWithProjectAreas() {
		return jpRepo.findAllWithProjectAreas();
	}

	@Override
	public JobPost getJobPostWithProjectAreas(int id) {
		return jpRepo.findByIdWithProjectAreas(id).orElse(null);
	}

	@Override
	public boolean addProjectAreatoPost(String username, int postId, int areaId) {
		ProjectArea area = paRepo.searchById(areaId);
		JobPost post = jpRepo.findByUser_UsernameAndId(username, postId);
		if (area != null && post != null) {
			post.addProjectArea(area);
			jpRepo.saveAndFlush(post);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeProjectAreatoPost(String username, int postId, int areaId) {
		ProjectArea area = paRepo.searchById(areaId);
		JobPost post = jpRepo.findByUser_UsernameAndId(username, postId);
		if (area != null && post != null) {
			post.removeProjectArea(area);
			jpRepo.saveAndFlush(post);
			return true;
		}
		return false;
	}

	@Override
	public boolean addTradetoPost(String username, int postId, int tradeId) {
		Trade trade = tradeRepo.searchById(tradeId);
		JobPost post = jpRepo.findByUser_UsernameAndId(username, postId);
		if (trade != null && post != null) {
			post.addTrade(trade);
			jpRepo.saveAndFlush(post);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeTradetoPost(String username, int postId, int tradeId) {
		Trade trade = tradeRepo.searchById(tradeId);
		JobPost post = jpRepo.findByUser_UsernameAndId(username, postId);
		if (trade != null && post != null) {
			post.removeTrade(trade);
			jpRepo.saveAndFlush(post);
			return true;
		}
		return false;
	}

	@Override
	public boolean addSpecialtytoPost(String username, int postId, int specId) {
		Specialty spec = specRepo.searchById(specId);
		JobPost post = jpRepo.findByUser_UsernameAndId(username, postId);
		if (spec != null && post != null) {
			post.addSpecialty(spec);
			jpRepo.saveAndFlush(post);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeSpecialtytoPost(String username, int postId, int specId) {
		Specialty spec = specRepo.searchById(specId);
		JobPost post = jpRepo.findByUser_UsernameAndId(username, postId);
		if (spec != null && post != null) {
			post.removeSpecialty(spec);
			jpRepo.saveAndFlush(post);
			return true;
		}
		return false;
	}

}
