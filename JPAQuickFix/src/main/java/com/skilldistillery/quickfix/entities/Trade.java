package com.skilldistillery.quickfix.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

	// ManyToMany JobPost
//	@ManyToMany
//	@JoinTable(name = "job_post_has_trade", 
//			joinColumns = @JoinColumn(name = "trade_id"), 
//			inverseJoinColumns = @JoinColumn(name = "job_post_id"))
//	private List<JobPost> jobPosts;

	// ManyToMany Provider
//	@ManyToMany
//	@JoinTable(name = "job_post_has_trade", 
//			joinColumns = @JoinColumn(name = "trade_id"), 
//			inverseJoinColumns = @JoinColumn(name = "provider_id"))
//	private List<Provider> providers;
	
	//OneToMany Speciality

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
