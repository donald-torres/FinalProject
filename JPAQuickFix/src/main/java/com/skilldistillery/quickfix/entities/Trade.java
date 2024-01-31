package com.skilldistillery.quickfix.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Trade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createDate;

	@Column(name = "update_date")
	@UpdateTimestamp
	private LocalDateTime updateDate;

	@Column(name = "image_url")
	private String imageUrl;

	@JsonIgnore
	@ManyToMany(mappedBy = "trades")
	private List<JobPost> jobPosts;

	@ManyToMany
	@JoinTable(name = "provider_has_trade", joinColumns = @JoinColumn(name = "trade_id"), inverseJoinColumns = @JoinColumn(name = "provider_id"))
	private List<Provider> providers;

	@JsonIgnore
	@OneToMany(mappedBy = "trade")
	private List<Specialty> specialties;

	public Trade() {
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

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
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

	public List<Specialty> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(List<Specialty> specialties) {
		this.specialties = specialties;
	}
	public void addSpecialties(Specialty specialty) {
		if (specialties == null) {
			specialties = new ArrayList<>();
		
		}
		if (!specialties.contains(specialty)) {
			specialties.add(specialty);
			if (specialty.getTrade() != null) {
				specialty.getTrade().removeSpecialty(specialty);
			}
		}

	}

	public void removeSpecialty(Specialty specialty) {
		if (specialties != null && specialties.contains(specialty)) {
			specialties.remove(specialty);
			specialty.setTrade(null);
		}
	}
	public void addProvider(Provider provider) {
		if (providers == null) {
			providers = new ArrayList<>();
		}
		if (!providers.contains(provider)) {
			providers.add(provider);
			provider.addTrade(this);
		}	}
	
	public void removeProvider(Provider provider) {
		if (providers != null && providers.contains(provider)) {
			providers.remove(provider);
			provider.removeTrade(this);
		}
	}
	public void addJobPost(JobPost jobPost) {
		if (jobPosts == null) {
			jobPosts = new ArrayList<>();
		}
		if (!jobPosts.contains(jobPost)) {
			jobPosts.add(jobPost);
			jobPost.addTrade(this);
		}	}

	public void removeJobPost(JobPost jobPost) {
		if (jobPosts != null && jobPosts.contains(jobPost)) {
			jobPosts.remove(jobPost);
			jobPost.removeTrade(this);
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
		Trade other = (Trade) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Trade [id=" + id + ", name=" + name + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", imageUrl=" + imageUrl + "]";
	}

}
