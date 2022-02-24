package com.sapient.controllers;

import java.util.Arrays;

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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CastController {

	
//	@Autowired
//	MovieDao movieDao;
//	
//	@RequestMapping(path = "/add-new-cast", method = RequestMethod.GET)
//	public String getCastFormForAdding(@RequestParam Integer id, Model model) {
//		Movie movie=movieDao.getMovieById(id);
//		
//		model.addAttribute("movieName", movie.getTitle());
//		model.addAttribute("movieId", movie.getMovieId());
//		return "/WEB-INF/pages/cast-form.jsp";
//	}
//
//	@RequestMapping(path = "/add-new-cast", method = RequestMethod.POST)
//	public String addNewMovie(@RequestParam("castlist") String castList, @RequestParam("movieId") Integer id, Errors errors) throws DaoException {
//		if (errors.hasErrors()) { // data conversion errors
//			// do custom validation
//			return "/WEB-INF/pages/movie-form.jsp";
//		}
////		@ModelAttribute("review") Review review, @ModelAttribute("cast") Cast cast,
//		Movie movie =movieDao.getMovieById(id);
//		System.out.println(castList);
//		
//		movie.setCasts(castList);
//		
////		String arr[]=castList.split(" ");
////		movie.setCasts(Arrays.asList(arr));
//		movieDao.updateMovie(movie);
//		
//		return "redirect:/movie-details?id=" + id;
//	}
//
//	@RequestMapping(path = "/edit-movie", method = RequestMethod.GET)
//	public String getMovieFormForEditing(@RequestParam Integer id, Model model) throws DaoException {
//		model.addAttribute("movie", movieDao.getMovieById(id));
//		return "/WEB-INF/pages/movie-form.jsp";
//	}
//
//	@RequestMapping(path = "/edit-movie", method = RequestMethod.POST)
//	public String updateMovie(@ModelAttribute Movie movie) throws DaoException {
//
//		movieDao.updateMovie(movie);
//		return "redirect:/movie-details?id=" + movie.getMovieId();
//	}
//
//	@RequestMapping("/all-movies")
//	public String getAllMovies(Model model) throws DaoException {
//		model.addAttribute("movies", movieDao.getMoviesList()); // spring keeps these in the request scope
//		return "/WEB-INF/pages/show-movies.jsp";
//	}
//
//	@RequestMapping("/movie-details")
//	public String getMovieById(@RequestParam Integer id, Model model) throws DaoException {
//		model.addAttribute("movie", movieDao.getMovieById(id));
//		return "/WEB-INF/pages/movie-details.jsp";
//	}
//
//	@RequestMapping("/delete-movie")
//	public String deleteMovie(@RequestParam Integer id) throws DaoException {
//		log.debug("Inside MovieController.deleteMovie, movieDao is an instanceof {} class",
//				movieDao.getClass().getName());
//		movieDao.deleteMovie(id);
//		return "redirect:/all-movies";
//	}
	
}
