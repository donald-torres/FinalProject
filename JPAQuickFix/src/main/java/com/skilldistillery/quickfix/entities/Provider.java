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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

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
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String description;

	@Column(name = "logo_url")
	private String logoUrl;

	@JsonIgnore
	@ManyToMany(mappedBy = "providers")
	private List<Trade> trades;

	@JsonIgnore
	@ManyToMany(mappedBy = "providers")
	private List<Specialty> specialties;

	@JsonIgnore
	@OneToMany(mappedBy = "provider")
	private List<Appointment> appointments;

	@JsonIgnore
	@OneToMany(mappedBy = "provider")
	private List<Bid> bids;

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
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
	public void addSpecialty(Specialty specialty) {
		if (specialties == null) {
			specialties = new ArrayList<>();
		}
		if (!specialties.contains(specialty)) {
			specialties.add(specialty);
			specialty.addProvider(this);
		}
		
	}
	
	public void removeSpecialty(Specialty specialty) {
		if (specialties != null && specialties.contains(specialty)) {
			specialties.remove(specialty);
			specialty.removeProvider(this);
			
		}
	}
	public void addTrade(Trade trade) {
		if (trades == null) {
			trades = new ArrayList<>();
		}
		if (!trades.contains(trade)) {
			trades.add(trade);
			trade.addProvider(this);
		}
		
	}
	
	public void removeTrade(Trade trade) {
		if (trades != null && trades.contains(trade)) {
			trades.remove(trade);
			trade.removeProvider(this);
			
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
