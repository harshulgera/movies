package com.sapient.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sapient.entity.Review;
import com.sapient.exception.DaoException;


	public interface ReviewDao {

	    // CRUD
		
		@Transactional
	    public Review addReview(Review review) throws DaoException;

//	    public Review getReview(int reviewId) throws DaoException;

		@Transactional
	    public Review updateReview(Review review) throws DaoException;

		@Transactional
	    public void deleteReview(int reviewId) throws DaoException;

		List<Review> getReviewByMovie(int reviewId) throws DaoException;

		List<Review> getReviewByUser(int userId) throws DaoException;
	    

	}
	
