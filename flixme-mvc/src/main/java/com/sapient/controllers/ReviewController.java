package com.sapient.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.dao.MovieDao;
import com.sapient.dao.ReviewDao;
import com.sapient.dao.UserDao;
import com.sapient.entity.Movie;
import com.sapient.entity.Review;
import com.sapient.entity.User;
import com.sapient.exception.DaoException;

@Controller
public class ReviewController 
{

	@Autowired
	ReviewDao reviewDao;
	
	@Autowired
	MovieDao movieDao;
	
	@Autowired
	UserDao userDao;
	
	
	@RequestMapping(path = "/add-new-review", method = RequestMethod.GET)
	public String getMovieFormForAdding(Model model, @RequestParam Integer id, HttpServletRequest request) 
	{
		Movie movie=movieDao.getMovieById(id);
//		System.out.println(movie);
//		model.addAttribute("movie", movie);
		request.getSession().setAttribute("currentMovie", movie);
		model.addAttribute("review",new Review());
		return "/WEB-INF/pages/review-form.jsp";
	}
	
	
	
	@RequestMapping(path = "/add-new-review", method = RequestMethod.POST)
	public String addNewMovie(@ModelAttribute("review") Review review, HttpServletRequest request, Errors errors) throws DaoException {
		if (errors.hasErrors()) { // data conversion errors
			// do custom validation
			return "/WEB-INF/pages/review-form.jsp";
		}
//		@ModelAttribute("review") Review review, @ModelAttribute("cast") Cast cast,
		Movie movie= (Movie) request.getSession().getAttribute("currentMovie");
//		System.out.println(movie);
		review.setMovie(movie);
		review.setUser((User) request.getSession().getAttribute("loggedInUser"));
		reviewDao.addReview(review);
		return "redirect:/movie-details?id=" + movie.getMovieId();
	}
	
	
	
	
}
