package com.sapient.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sapient.entity.Movie;
import com.sapient.entity.Review;
import com.sapient.exception.DaoException;

public class MovieDaoImpl implements MovieDao {

    private static HashMap<Integer, Movie> movies = new HashMap<>();
    static int cnt = 100;

    @Override
    public void addMovie(Movie movie) throws DaoException {
        movie.setMovieId(cnt);
        movies.put(cnt++, movie);
    }

    @Override
    public Movie getMovie(int movieId) throws DaoException {
        return movies.get(movieId);
    }

    @Override
    public void updateMovie(Movie movie) throws DaoException {
        movies.put(movie.getMovieId(), movie);
    }

    @Override
    public void deleteMovie(int movieId) throws DaoException {
        movies.remove(movieId);
    }

    @Override
    public List<Movie> listMovies() throws DaoException {
        List<Movie> moviesList = new ArrayList<>();
        for (Movie movie : movies.values())
            moviesList.add(movie);

        return moviesList;
    }

	@Override
	public void addReview(Review review) throws DaoException {
		Movie movie= getMovie(review.getMovieId());
		List<Review> reviews= movie.getReview();
		if(reviews==null)
			reviews= new ArrayList<>();
		
		reviews.add(review);
	}

	@Override
	public void deleteReview(Review review) throws DaoException {
		Movie movie= getMovie(review.getMovieId());
		List<Review> reviews= movie.getReview();
		reviews.remove(review);
	}
    
    

}
