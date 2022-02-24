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
	public String addNewUser(@ModelAttribute("user") User user, HttpServletRequest request ,Errors errors) throws DaoException {
		if (errors.hasErrors()) { // data conversion errors
			// do custom validation
			
			return "/WEB-INF/pages/user-form.jsp";
		}
		
		user=userDao.addUser(user);
		request.getSession().setAttribute("loggedInUser", user);
		return "redirect:/all-movies";
	}

	@RequestMapping(path = "/edit-user", method = RequestMethod.GET)
	public String getUserFormForEditing(@RequestParam Integer id, Model model) throws DaoException {
		model.addAttribute("user", userDao.getUser(id));
		return "/WEB-INF/pages/user-form.jsp";
	}

	@RequestMapping(path = "/edit-user", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute User user, Model model) throws DaoException {
		userDao.updateUser(user);
		return "redirect:/user-details?id=" + user.getUserId();
	}	

}
