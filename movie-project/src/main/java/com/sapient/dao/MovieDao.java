package com.sapient.dao;

import java.util.List;

import com.sapient.entity.Movie;
import com.sapient.entity.Review;
import com.sapient.exception.DaoException;

public interface MovieDao {

    // CRUD
    public void addMovie(Movie movie) throws DaoException;


    public void updateMovie(Movie movie) throws DaoException;

    public void deleteMovie(int movieId) throws DaoException;

    public List<Movie> listMovies() throws DaoException;
    
    public void addReview(Review review) throws DaoException;
    
    public void deleteReview(Review review) throws DaoException;

//QUERY    
    public Movie getMovie(int movieId) throws DaoException;
    
    
}
