package com.skilldistillery.quickfix.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Bid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double amount;

	@ManyToOne
	@JoinColumn(name="user_provider_id")
	private Provider provider;
//	@JsonIgnore
	@JsonIgnoreProperties({"trades", "specialties", "appointments", "bids", "projectAreas"})
	@ManyToOne
	@JoinColumn(name = "post_id")
	private JobPost jobPost;

	@Column(name = "bid_date")
	private LocalDateTime bidDate;

	@Column(name = "provider_note")
	private String providerNote;

	private Boolean accepted;

	@Column(name = "rating_by_user")
	private Integer ratingByUser;

	@Column(name = "user_comment")
	private String userComment;

	@Column(name = "rating_by_provider")
	private Integer ratingByProvider;

	@Column(name = "provider_comment")
	private String providerComment;

	private Boolean enabled;
	@JsonIgnore
	@OneToMany(mappedBy="bid")
	private List<BidComment> bidComments;

	public Bid() {
		super();
	}

	public List<BidComment> getBidComments() {
		return bidComments;
	}

	public void setBidComments(List<BidComment> bidComments) {
		this.bidComments = bidComments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public JobPost getJobPost() {
		return jobPost;
	}

	public void setJobPost(JobPost jobPost) {
		this.jobPost = jobPost;
	}

	public LocalDateTime getBidDate() {
		return bidDate;
	}

	public void setBidDate(LocalDateTime bidDate) {
		this.bidDate = bidDate;
	}

	public String getProviderNote() {
		return providerNote;
	}

	public void setProviderNote(String providerNote) {
		this.providerNote = providerNote;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public Integer getRatingByUser() {
		return ratingByUser;
	}

	public void setRatingByUser(Integer ratingByUser) {
		this.ratingByUser = ratingByUser;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public Integer getRatingByProvider() {
		return ratingByProvider;
	}

	public void setRatingByProvider(Integer ratingByProvider) {
		this.ratingByProvider = ratingByProvider;
	}

	public String getProviderComment() {
		return providerComment;
	}

	public void setProviderComment(String providerComment) {
		this.providerComment = providerComment;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public void addBidComments(BidComment bidComment) {
		if (bidComments == null) {
			bidComments = new ArrayList<>();
		
		}
		if (!bidComments.contains(bidComment)) {
			bidComments.add(bidComment);
			if (bidComment.getBid() != null) {
				bidComment.getBid().removeBidComment(bidComment);
			}
		}

	}

	public void removeBidComment(BidComment bidComment) {
		if (bidComments != null && bidComments.contains(bidComment)) {
			bidComments.remove(bidComment);
			bidComment.setBid(null);
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
		Bid other = (Bid) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Bid [id=" + id + ", amount=" + amount + ", jobPost=" + jobPost + ", bidDate=" + bidDate
				+ ", providerNote=" + providerNote + ", accepted=" + accepted + ", ratingByUser=" + ratingByUser
				+ ", userComment=" + userComment + ", ratingByProvider=" + ratingByProvider + ", providerComment="
				+ providerComment + ", enabled=" + enabled + ", bidComments=" + bidComments + "]";
	}

}
