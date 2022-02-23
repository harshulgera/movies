package com.sapient.dao;

import java.util.HashMap;

import com.sapient.entity.Review;
import com.sapient.exception.DaoException;

public class ReviewDaoImpl implements ReviewDao {

    private static HashMap<Integer, Review> reviews = new HashMap<>();
    static int cnt = 200;

    @Override
    public Review addReview(Review review) throws DaoException {
    	review.setReviewId(cnt);
    	 reviews.put(cnt, review);
        cnt++;
        return review;
    }

    @Override
    public Review getReview(int reviewId) throws DaoException {
        return reviews.get(reviewId);
    }

    @Override
    public void updateReview(Review review) throws DaoException {
        reviews.put(review.getReviewId(), review);
    }

    @Override
    public void deleteReview(int reviewId) throws DaoException {
        reviews.remove(reviewId);
    }
    
    

}


//
//package com.sapient.dao;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import com.sapient.entity.Review;
//import com.sapient.exception.DaoException;
//
//public class ReviewDaoImpl implements ReviewDao {
//
//    private static HashMap<Integer, Review> reviews = new HashMap<>();
//    private static HashMap<Integer, List<Integer>> reviewsByMovie = new HashMap<>();
//    private static HashMap<Integer, List<Integer>> reviewsByUser = new HashMap<>();
//    static int cnt = 200;
//
//    @Override
//    public Review addReview(Review review) throws DaoException {
//    	review.setReviewId(cnt);
//        reviews.put(cnt, review);
//        int movieId = review.getMovieId();
//        if (reviewsByMovie.containsKey(movieId))
//            reviewsByMovie.get(movieId).add(cnt);
//        else {
//            List<Integer> movieReviews = new ArrayList<Integer>();
//            movieReviews.add(cnt);
//            reviewsByMovie.put(movieId, movieReviews);
//        }
//        int userId = review.getUserId();
//        if (reviewsByUser.containsKey(userId))
//            reviewsByUser.get(userId).add(cnt);
//        else {
//            List<Integer> userReviews = new ArrayList<Integer>();
//            userReviews.add(cnt);
//            reviewsByMovie.put(movieId, userReviews);
//        }
//        cnt++;
//        return review;
//    }
//
//    @Override
//    public Review getReview(int reviewId) throws DaoException {
//        return reviews.get(reviewId);
//    }
//
//    @Override
//    public void updateReview(Review review) throws DaoException {
//        reviews.put(review.getReviewId(), review);
//    }
//
//    @Override
//    public void deleteReview(int reviewId) throws DaoException {
//        int userId = reviews.get(reviewId).getUserId();
//        int movieId = reviews.get(reviewId).getMovieId();
//        reviewsByMovie.get(movieId).remove(reviewId);
//        reviewsByUser.get(userId).remove(reviewId);
//        reviews.remove(reviewId);
//    }
//    
//    public List<Review> getReviewsByMovie(int movieId) throws DaoException
//    {
//    	List<Review> reviewList= new ArrayList<>();
//    	List<Integer> reviewIds=reviewsByMovie.get(movieId);
//    	for(int rid: reviewIds)
//    		reviewList.add(reviews.get(rid));
//    	return reviewList;
//    }
//    
//    public List<Review> getReviewsByUser(int userId) throws DaoException
//    {
//    	List<Review> reviewList= new ArrayList<>();
//    	List<Integer> reviewIds=reviewsByMovie.get(userId);
//    	for(int rid: reviewIds)
//    		reviewList.add(reviews.get(rid));
//    	return reviewList;
//    }
//
//}
//
