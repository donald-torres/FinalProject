package com.skilldistillery.quickfix.entities;

import java.time.LocalDate;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_post")
public class JobPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;
	private String description;

	@CreationTimestamp
	@Column(name = "create_date")
	private LocalDateTime createDate;

	@UpdateTimestamp
	@Column(name = "update_date")
	private LocalDateTime updateDate;

	private Boolean complete;
	private Boolean enabled;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "special_instructions")
	private String specialInstructions;

	@Column(name = "materials_provided")
	private Boolean materialsProvided;

	@Column(name = "image_url")
	private String imageUrl;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "budget_max")
	private Double budgetMax;

	@Column(name = "bid_by")
	private LocalDateTime bidBy;

	@ManyToMany
	@JoinTable(name = "job_post_has_project_area", joinColumns = @JoinColumn(name = "job_post_id"), inverseJoinColumns = @JoinColumn(name = "project_area_id"))
	private List<ProjectArea> projectAreas;

	@JsonIgnore
	@OneToMany(mappedBy = "jobPost")
	private List<Appointment> appointments;

	@ManyToMany
	@JoinTable(name = "job_post_has_trade", joinColumns = @JoinColumn(name = "job_post_id"), inverseJoinColumns = @JoinColumn(name = "trade_id"))
	private List<Trade> trades;

	@ManyToMany
	@JoinTable(name = "focus_has_job_post", joinColumns = @JoinColumn(name = "job_post_id"), inverseJoinColumns = @JoinColumn(name = "focus_id"))
	private List<Specialty> specialties;

	@JsonIgnore
	@OneToMany(mappedBy = "jobPost")
	private List<Bid> bids;

	public JobPost() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}

	public Boolean getMaterialsProvided() {
		return materialsProvided;
	}

	public void setMaterialsProvided(Boolean materialsProvided) {
		this.materialsProvided = materialsProvided;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getBudgetMax() {
		return budgetMax;
	}

	public void setBudgetMax(Double budgetMax) {
		this.budgetMax = budgetMax;
	}

	public LocalDateTime getBidBy() {
		return bidBy;
	}

	public void setBidBy(LocalDateTime bidBy) {
		this.bidBy = bidBy;
	}

	public List<ProjectArea> getProjectAreas() {
		return projectAreas;
	}

	public void setProjectAreas(List<ProjectArea> projectAreas) {
		this.projectAreas = projectAreas;
	}

	public void addProjectArea(ProjectArea projectArea) {
		if (projectAreas == null) {
			projectAreas = new ArrayList<>();
		}
		if (!projectAreas.contains(projectArea)) {
			projectAreas.add(projectArea);
			projectArea.addJobPost(this);
		}

	}

	public void removeProjectArea(ProjectArea projectArea) {
		if (projectAreas != null && projectAreas.contains(projectArea)) {
			projectAreas.remove(projectArea);
			projectArea.removeJobPost(this);

		}
	}
	public void addSpecialty(Specialty specialty) {
		if (specialties == null) {
			specialties = new ArrayList<>();
		}
		if (!specialties.contains(specialty)) {
			specialties.add(specialty);
			specialty.addJobPost(this);
		}
		
	}
	
	public void removeSpecialty(Specialty specialty) {
		if (specialties != null && specialties.contains(specialty)) {
			specialties.remove(specialty);
			specialty.removeJobPost(this);
			
		}
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	public List<Specialty> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(List<Specialty> specialties) {
		this.specialties = specialties;
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
		JobPost other = (JobPost) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "JobPost [id=" + id + ", title=" + title + ", description=" + description + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", complete=" + complete + ", enabled=" + enabled + ", startDate="
				+ startDate + ", specialInstructions=" + specialInstructions + ", materialsProvided="
				+ materialsProvided + ", imageUrl=" + imageUrl + ", User=" + user + ", budgetMax=" + budgetMax
				+ ", bidBy=" + bidBy + ", projectAreas=" + projectAreas + ", bids=" + bids + "]";
	}

}
