package com.sapient.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sapient.entity.Movie;
import com.sapient.exception.DaoException;


public interface MovieDao {

    // CRUD
	@Transactional
    public Movie addMovie(Movie movie) throws DaoException;

	@Transactional
    public Movie updateMovie(Movie movie) throws DaoException;

	@Transactional
    public void deleteMovie(int movieId) throws DaoException;

    public List<Movie> getMoviesList() throws DaoException;
    

//QUERY    
    public Movie getMovieById(int movieId) throws DaoException;
    
    
}
