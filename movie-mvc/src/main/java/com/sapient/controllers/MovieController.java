package com.sapient.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.dao.MovieDao;
import com.sapient.entity.Movie;
import com.sapient.exception.DaoException;
import com.sapient.utils.DisplayUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MovieController 
{
	
	@Autowired
	MovieDao movieDao;

	@RequestMapping(path = "/add-new-movie", method = RequestMethod.GET)
	public String getMovieFormForAdding(Model model) {
		model.addAttribute("movie", new Movie());
//		.addAttribute("cast",new Cast());
//		.addAttribute("review",new Review())
		return "/WEB-INF/pages/movie-form.jsp";
	}

	@RequestMapping(path = "/add-new-movie", method = RequestMethod.POST)
	public String addNewMovie(@ModelAttribute("movie") Movie movie, Errors errors) throws DaoException {
		if (errors.hasErrors()) { // data conversion errors
			// do custom validation
			return "/WEB-INF/pages/movie-form.jsp";
		}
//		@ModelAttribute("review") Review review, @ModelAttribute("cast") Cast cast,
		movieDao.addMovie(movie);
		
		return "redirect:/movie-details?id=" + movie.getMovieId();
	}

	@RequestMapping(path = "/edit-movie", method = RequestMethod.GET)
	public String getMovieFormForEditing(@RequestParam Integer id, Model model) throws DaoException {
		model.addAttribute("movie", movieDao.getMovieById(id));
		model.addAttribute("castList",DisplayUtil.converToList(movieDao.getMovieById(id).getCasts()));
		return "/WEB-INF/pages/movie-form.jsp";
	}

	@RequestMapping(path = "/edit-movie", method = RequestMethod.POST)
	public String updateMovie(@ModelAttribute Movie movie, Model model) throws DaoException {
		movie=movieDao.updateMovie(movie);
		model.addAttribute("movie", movie);
		return "redirect:/movie-details?id=" + movie.getMovieId();
	}

	@RequestMapping(path="/all-movies",method = RequestMethod.GET)
	public String getAllMovies(Model model) throws DaoException {
		model.addAttribute("movies", movieDao.getMoviesList()); // spring keeps these in the request scope
		return "/WEB-INF/pages/show-movies.jsp";
	}

	@RequestMapping("/movie-details")
	public String getMovieById(@RequestParam Integer id, Model model) throws DaoException {
		Movie movie=movieDao.getMovieById(id);
		model.addAttribute("movie", movie);
		model.addAttribute("reviewsList", movie.getReviews());
		model.addAttribute("castList",DisplayUtil.converToList(movie.getCasts()));
		return "/WEB-INF/pages/movie-details.jsp";
	}

	@RequestMapping("/delete-movie")
	public String deleteMovie(@RequestParam Integer id) throws DaoException {
		log.debug("Inside MovieController.deleteMovie, movieDao is an instanceof {} class",
				movieDao.getClass().getName());
		movieDao.deleteMovie(id);
		return "redirect:/all-movies";
	}
	
	@RequestMapping(path="/all-movies",method = RequestMethod.POST)
	public String getMoviesBySearch(Model model, @RequestParam("searchTitle") String searchTitle) throws DaoException {
		
		List<Movie> movies=movieDao.getMoviesList();
		List<Movie> movieList= new ArrayList<Movie>();
		for(Movie movie: movies)
		{
			if(movie.getTitle().toLowerCase().contains(searchTitle.toLowerCase()))
				movieList.add(movie);
			else if(movie.getGenre().toLowerCase().contains(searchTitle.toLowerCase()))
				movieList.add(movie);
			else if(Integer.toString(movie.getYear()).contains(searchTitle.toLowerCase()))
				movieList.add(movie);
			else if(movie.getCasts().toLowerCase().contains(searchTitle.toLowerCase()))
				movieList.add(movie);
		}
		
		model.addAttribute("movies", movieList); 
		return "/WEB-INF/pages/show-movies.jsp";
	}
	

}
