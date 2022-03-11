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
import com.sapient.utils.DisplayUtil;

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
	public String getReviewFormForAdding(Model model, @RequestParam Integer id, HttpServletRequest request) 
	{
		Movie movie=movieDao.getMovieById(id);
		request.getSession().setAttribute("currentMovie", movie);
		model.addAttribute("review",new Review());
		return "/WEB-INF/pages/review-form.jsp";
	}
	
	
	
	@RequestMapping(path = "/add-new-review", method = RequestMethod.POST)
	public String addNewReview(@ModelAttribute("review") Review review, HttpServletRequest request, Errors errors) throws DaoException {
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
		request.getSession().removeAttribute("currentMovie");
		return "redirect:/movie-details?id=" + movie.getMovieId();
	}
	
	@RequestMapping(path = "/show-user-reviews")
	public String getUserReviews(@RequestParam Integer id, Model model) throws DaoException {
		User user= userDao.getUser(id);
		model.addAttribute("reviewsList", user.getReview());
		return "/WEB-INF/pages/show-reviews.jsp";
	}
	
	@RequestMapping(path = "/edit-movie-review")
	public String getMovieReviews(@RequestParam Integer id, Model model,HttpServletRequest request) throws DaoException {
		Movie movie= movieDao.getMovieById(id);
		request.getSession().setAttribute("currentMovie", movie);
		model.addAttribute("reviewsList", movie.getReviews());
		return "/WEB-INF/pages/show-reviews.jsp";
	}
	
	@RequestMapping(path = "/delete-movie-review", method = RequestMethod.GET)
	public String deleteMovieReview(@RequestParam Integer id,HttpServletRequest request) 
	{
		Review review= reviewDao.getReview(id);
		reviewDao.deleteReview(review);
		Movie movie=((Movie)request.getSession().getAttribute("currentMovie"));
		request.getSession().removeAttribute("currentMovie");
		return "redirect:/edit-movie-review?id="+movie.getMovieId();
	}
	
	@RequestMapping(path = "/edit-review", method = RequestMethod.GET)
	public String getReviewFormForEditing(Model model, @RequestParam Integer id, HttpServletRequest request) 
	{
		Review review= reviewDao.getReview(id);
		Movie movie=review.getMovie();
		model.addAttribute("review",review);
		request.getSession().setAttribute("currentMovie", movie);
		model.addAttribute("movie",movie);
		return "/WEB-INF/pages/review-form.jsp";
	}
	
	@RequestMapping(path = "/edit-review", method = RequestMethod.POST)
	public String getReviewFormForUpdating(Model model,@ModelAttribute("review") Review review, HttpServletRequest request) 
	{
		User user=((User)request.getSession().getAttribute("loggedInUser"));
		Movie movie=((Movie)request.getSession().getAttribute("currentMovie"));
		review.setUser(user);
		review.setMovie(movie);
		request.getSession().removeAttribute("currentMovie");
		reviewDao.updateReview(review);
		return "redirect:/show-user-reviews?id="+user.getUserId();
	}
	
	@RequestMapping(path = "/delete-review", method = RequestMethod.GET)
	public String deleteUserReview(@RequestParam Integer id,HttpServletRequest request) 
	{
		Review review= reviewDao.getReview(id);
		reviewDao.deleteReview(review);
		User user=((User)request.getSession().getAttribute("loggedInUser"));
		return "redirect:/show-user-reviews?id="+user.getUserId();
	}
	
	
	
	
	
}
