package com.sapient.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapient.entity.Movie;
import com.sapient.entity.Review;
import com.sapient.exception.DaoException;

@Repository("reviewDao")
public class ReviewDaoImpl implements ReviewDao
{

	@Autowired
	HibernateTemplate template;
	
	@Autowired
	MovieDao movieDao;
	
	@Override
	public Review addReview(Review review) throws DaoException {
		try {
			template.persist(review);
			review.setReviewId(review.getReviewId());
			return review;
		} catch (DataAccessException e) {
			throw new DaoException("Could Not Add Review",e);
		}
	}

	@Override
	public List<Review> getReviewByMovie(int movieId) throws DaoException {
		try {
			Movie movie = movieDao.getMovieById(movieId);
			return movie.getReviews();
		} catch (DataAccessException e) {
			throw new DaoException("Could Not Get Reviews",e);
		}
	}
	
	@Override
	public List<Review> getReviewByUser(int userId) throws DaoException {
		return null;
	}

	@Override
	public Review updateReview(Review review) throws DaoException {
		return null;
	}

	@Override
	public void deleteReview(int reviewId) throws DaoException {
		// TODO Auto-generated method stub
		
	}
	

}
