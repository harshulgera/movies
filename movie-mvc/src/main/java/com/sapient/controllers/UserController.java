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

import com.sapient.dao.UserDao;
import com.sapient.entity.User;
import com.sapient.exception.DaoException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping(path = "/add-new-user", method = RequestMethod.GET)
	public String getUserFormForAdding(Model model) {
		model.addAttribute("user", new User());
		return "/WEB-INF/pages/user-form.jsp";
	}

	@RequestMapping(path = "/add-new-user", method = RequestMethod.POST)
	public String addNewUser(@ModelAttribute("user") User user,HttpServletRequest request , @RequestParam("confirmPassword")String cnfPass,Errors errors) throws DaoException {
		if (errors.hasErrors()) {
			return "/WEB-INF/pages/user-form.jsp";
		}
		
		try {
			if(cnfPass.equals(user.getPassword()))
			{
				user=userDao.addUser(user);		
				request.getSession().setAttribute("loggedInUser", user);
				return "redirect:/all-movies";
			}	
		}
		catch (DaoException e) {
			log.debug("Error Occured in Adding New User"+e.getMessage());
		}
		return "redirect:/add-new-user";
		
	}

	@RequestMapping(path = "/edit-user", method = RequestMethod.GET)
	public String getUserFormForEditing(@RequestParam Integer id, Model model, HttpServletRequest request) throws DaoException {
		request.getSession().setAttribute("disabledEmail", true);
		model.addAttribute("user", userDao.getUser(id));
		return "/WEB-INF/pages/user-form.jsp";
	}

	@RequestMapping(path = "/edit-user", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute User user, Model model) throws DaoException {
		userDao.updateUser(user);
		return "redirect:/show-user-details?id=" + user.getUserId();
	}	
	
	@RequestMapping(path = "/show-user-details", method = RequestMethod.GET)
	public String showUSerDetails(@RequestParam Integer id, Model model, HttpServletRequest request) throws DaoException {
		User user=userDao.getUser(id);
		model.addAttribute("user", user);
		model.addAttribute("reviewsList", user.getReview());
		return "/WEB-INF/pages/user-details.jsp";
	}
	
	
	@RequestMapping(path = "/login-user", method = RequestMethod.GET)
	public String getLoginForm() {
		return "/WEB-INF/pages/login-form.jsp";
	}

	@RequestMapping(path = "/login-user", method = RequestMethod.POST)
	public String handleLogin( @RequestParam("password") String password, @RequestParam String email,HttpServletRequest request) {
		User user=userDao.getUserByMail(email);
		if(user.getPassword().equals(password))
		{
			request.getSession().setAttribute("loggedInUser", user);
			return "redirect:/all-movies";
		}
		
		return "/WEB-INF/pages/login-form.jsp";
	}
	
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logoutUser(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/login-user";
	}
	
}
