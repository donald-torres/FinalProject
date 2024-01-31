package com.skilldistillery.quickfix.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "project_area")
public class ProjectArea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@ManyToMany(mappedBy = "projectAreas")
	@JsonIgnore
	private List<JobPost> jobPosts;

	public ProjectArea() {
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

	public List<JobPost> getJobPosts() {
		return jobPosts;
	}

	public void setJobPosts(List<JobPost> jobPosts) {
		this.jobPosts = jobPosts;
	}

	public void addJobPost(JobPost jobPost) {
		// FIXME
	}

	public void removeJobPost(JobPost jobPost) {
		// FIXME

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
		ProjectArea other = (ProjectArea) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "ProjectArea [id=" + id + ", name=" + name + ", jobPosts=" + jobPosts + "]";
	}

}
