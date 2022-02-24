package com.sapient.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapient.entity.Movie;
import com.sapient.entity.Review;
import com.sapient.exception.DaoException;

@Repository("movieDao")
public class MovieDaoImpl implements MovieDao {
	
	@Autowired
	HibernateTemplate tempelate;

	@Override
	public Movie addMovie(Movie movie) throws DaoException {
		try {
			movie.setReviews(new ArrayList<Review>());
			tempelate.persist(movie);
			movie.setMovieId(movie.getMovieId());
			return movie;
		} catch (DataAccessException e) {
			throw new DaoException("Could Not Add Movie",e);
		}
	}

	@Override
	public Movie updateMovie(Movie movie) throws DaoException {
		try {
			tempelate.merge(movie);
			return movie;
		} catch (DataAccessException e) {
			throw new DaoException("Could Not Update Movie",e);
		}
	}

	@Override
	public void deleteMovie(int movieId) throws DaoException {
		Movie movie=this.getMovieById(movieId);
		try {
			tempelate.delete(movie);
		} catch (DataAccessException e) {
			throw new DaoException("Could Not Delete Movie",e);
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Movie> getMoviesList() throws DaoException {
		try {
			return (List<Movie>) tempelate.find("from Movie order by movieId");
		} catch (DataAccessException e) {
			throw new DaoException("Could Not Add Movie",e);
		}
	}
	
	
	@Override
	public Movie getMovieById(int movieId) throws DaoException {
		try {
			return tempelate.get(Movie.class, movieId);
		} catch (DataAccessException e) {
			throw new DaoException("Could Not Add Movie",e);
		}
		
	}
	
	
}
