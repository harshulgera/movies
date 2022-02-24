package com.sapient.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"movie","user"})
@Entity
@Table(name="REVIEWS")
public class Review {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int reviewId;
	private String views;
	private Double rating;
	
	@ManyToOne
	private Movie movie;
	
	@ManyToOne
	private User user;
	
}
