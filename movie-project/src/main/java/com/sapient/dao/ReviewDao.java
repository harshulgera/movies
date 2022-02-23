package com.sapient.dao;

import com.sapient.entity.Review;
import com.sapient.exception.DaoException;

public interface ReviewDao {

    // CRUD
    public Review addReview(Review review) throws DaoException;

    public Review getReview(int reviewId) throws DaoException;

    public void updateReview(Review review) throws DaoException;

    public void deleteReview(int reviewId) throws DaoException;
    
    //QUERY
    

}
