package com.sapient.service;

import java.util.List;

import com.sapient.dao.MovieDao;
import com.sapient.dao.MovieDaoImpl;
import com.sapient.dao.ReviewDao;
import com.sapient.dao.ReviewDaoImpl;
import com.sapient.dao.UserDao;
import com.sapient.dao.UserDaoImpl;
import com.sapient.entity.Review;
import com.sapient.entity.User;
import com.sapient.exception.DaoException;
import com.sapient.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReviewService {

    ReviewDao reviewDao;
    UserDao userDao;
    MovieDao movieDao;
    UserService us;
    Review review;

    public ReviewService() {
        reviewDao = new ReviewDaoImpl();
        userDao = new UserDaoImpl();
        movieDao= new MovieDaoImpl();
        us=new UserService();
    }

    public void addReviewService(int movieId, int userId, String views, Double rating) {
        review = new Review();
        review.setMovieId(movieId);
        review.setUserId(userId);
        review.setRating(rating);
        review.setReview(views);
        try {
            review= reviewDao.addReview(review);
            movieDao.addReview(review);
            userDao.addReview(review);
        } catch (DaoException ex) {
        	log.debug(ex.getMessage());
            throw new ServiceException("Could Not add Review", ex);
        }
    }

    public void updateReviewService(Review review) {
        try {
            reviewDao.updateReview(review);
        } catch (DaoException ex) {
        	log.debug(ex.getMessage());
            throw new ServiceException("Could Not add Review", ex);
        }
    }

    public Review getReviewService(int reviewId) {
        try {
            return reviewDao.getReview(reviewId);
        } catch (DaoException ex) {
        	log.debug(ex.getMessage());
            throw new ServiceException("Could Not get Review", ex);
        }
    }

    public void deleteReviewService(int reviewId) {
        try {
        	Review review=getReviewService(reviewId);
            movieDao.deleteReview(review);
            userDao.deleteReview(review);
            reviewDao.deleteReview(reviewId);
        } catch (DaoException ex) {
        	log.debug(ex.getMessage());
            throw new ServiceException("Could Not Delete Review", ex);
        }
    }
    
//    public List<Review> getReviewsByMovieId(int reviewId)
//    {
//    	try {
//            return reviewDao.getReviewsByMovie(reviewId);
//        } catch (DaoException ex) {
//            throw new ServiceException("Could Not Get Reviews", ex);
//        }
//    }
    
    public boolean checkReviewByUserExists(int movieId, int userId)
    {
    	User user = us.getUserService(userId);
    	List<Review> reviewList=user.getReview();
    	for(Review rvw: reviewList)
    	{
    		if(rvw.getMovieId()==movieId)
    			return true;
    	}
    	return false;
    }
    
//    public List<Review> getReviewsByUserId(int userId)
//    {
//    	try {
//            return reviewDao.getReviewsByUser(userId);
//        } catch (DaoException ex) {
//            throw new ServiceException("Could Not Get Reviews", ex);
//        }
//    }
    
}
