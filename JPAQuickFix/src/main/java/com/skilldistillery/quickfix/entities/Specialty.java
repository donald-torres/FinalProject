package com.skilldistillery.quickfix.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Specialty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;

	@Column(name = "image_url")
	private String imageUrl;

	@JsonIgnore
	@ManyToMany(mappedBy = "specialties")
	private List<JobPost> jobPosts;

	@ManyToMany
	@JoinTable(name = "provider_has_focus", joinColumns = @JoinColumn(name = "focus_id"), inverseJoinColumns = @JoinColumn(name = "provider_id"))
	private List<Provider> providers;
	
	@ManyToOne
	@JoinColumn(name = "trade_id")
	private Trade trade;

	public Specialty() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<JobPost> getJobPosts() {
		return jobPosts;
	}

	public void setJobPosts(List<JobPost> jobPosts) {
		this.jobPosts = jobPosts;
	}

	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}
	public void addJobPost(JobPost jobPost) {
		if (jobPosts == null) {
			jobPosts = new ArrayList<>();
		}
		if (!jobPosts.contains(jobPost)) {
			jobPosts.add(jobPost);
			jobPost.addSpecialty(this);
		}	}

	public void removeJobPost(JobPost jobPost) {
		if (jobPosts != null && jobPosts.contains(jobPost)) {
			jobPosts.remove(jobPost);
			jobPost.removeSpecialty(this);
		}
	}
	public void addProvider(Provider provider) {
		if (providers == null) {
			providers = new ArrayList<>();
		}
		if (!providers.contains(provider)) {
			providers.add(provider);
			provider.addSpecialty(this);
		}	}
	
	public void removeProvider(Provider provider) {
		if (providers != null && providers.contains(provider)) {
			providers.remove(provider);
			provider.removeSpecialty(this);
		}
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Specialty other = (Specialty) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Specialty [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl
				+ "]";
	}

}
