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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Provider {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String company;

	private String email;

	private String phone;

	@Column(name = "rate_per_hour")
	private Double ratePerHour;

	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createDate;

	@Column(name = "update_date")
	@UpdateTimestamp
	private LocalDateTime updateDate;

	private boolean enabled = true;

//	@ManyToOne
//	@JoinColumn(name="address_id")
//	private Address address;

//	@ManyToOne
//  @JoinColumn(name = "user_id")
//  private User user;

	private String description;

	@Column(name = "logo_url")
	private String logoUrl;

	// ManyToMany Trade

	// ManyToMany Specialty

//	@OneToMany(mappedBy = "provider")
//  private List<Appointment> appointments;

	public Provider() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getRatePerHour() {
		return ratePerHour;
	}

	public void setRatePerHour(Double ratePerHour) {
		this.ratePerHour = ratePerHour;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
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
		Provider other = (Provider) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Provider [id=" + id + ", company=" + company + ", email=" + email + ", phone=" + phone
				+ ", ratePerHour=" + ratePerHour + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", enabled=" + enabled + ", description=" + description + ", logoUrl=" + logoUrl + "]";
	}

}
