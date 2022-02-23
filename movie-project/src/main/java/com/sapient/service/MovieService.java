package com.sapient.service;

import java.util.ArrayList;
import java.util.List;

import com.sapient.dao.MovieDao;
import com.sapient.dao.MovieDaoImpl;
import com.sapient.entity.Genre;
import com.sapient.entity.Movie;
import com.sapient.entity.Review;
import com.sapient.exception.DaoException;
import com.sapient.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MovieService {

    MovieDao movieDao;
    Movie movie;

    public MovieService() {
        this.movieDao = new MovieDaoImpl();
    }

    public void addMovieService(String title, int year, Genre genre, List<String> cast) {

        movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setCast(cast);
        movie.setYear(year);
        movie.setReview(new ArrayList<Review>());
        try {
            movieDao.addMovie(movie);
        } catch (DaoException ex) {
        	log.debug(ex.getMessage());
            throw new ServiceException("Could Not Add Movie", ex);
        }

    }

    public Movie getMovieService(int id) {
        try {
            return movieDao.getMovie(id);
        } catch (DaoException ex) {
        	log.debug(ex.getMessage());
            throw new ServiceException("Could Not Fetch Movie", ex);
        }
    }

    public void updateMovieService(Movie movie) {
        try {
            movieDao.updateMovie(movie);
        } catch (DaoException e) {
        	log.debug(e.getMessage());
            throw new ServiceException("Could Not Update Movie");
        }
    }

    public void deleteMovie(int id) {
        try {
            movieDao.deleteMovie(id);
        } catch (DaoException e) {
        	log.debug(e.getMessage());
            throw new ServiceException("Could not Delete Movie");
        }
    }

    public List<Movie> getAllMovies() {
        try {
            return movieDao.listMovies();
        } catch (DaoException e) {
        	log.debug(e.getMessage());
            throw new ServiceException("Could not Fetch Movie");
        }

    }
    
    public List<Movie> searchMovieByName(String searchString)
    {
    	List<Movie> res=new ArrayList<>();
    	try {
    		List<Movie> movies= movieDao.listMovies();
            if(movies.size()!=0) {
            	for(Movie m: movies)
            		if(m.getTitle().toLowerCase().contains(searchString.toLowerCase()))
            			res.add(m);
            }
            return res;
        } catch (DaoException e) {
        	log.debug(e.getMessage());
            throw new ServiceException("Could not Fetch Movie");
        }
    
    	
    }

   

}
