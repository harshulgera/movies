package com.sapient.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.sapient.dao.UserDao;
import com.sapient.dao.UserDaoImpl;
import com.sapient.entity.Review;
import com.sapient.entity.User;
import com.sapient.exception.DaoException;
import com.sapient.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserService {

    UserDao userDao;
    User user;

    public UserService() {
        userDao = new UserDaoImpl();
    }

    public User addUserService(String name, String number, String username, String password, Double balance) {
        user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setNumber(number);
        user.setPassword(password);
        user.setBalance(balance);
        user.setReview(new ArrayList<Review>());
        try {
            user=userDao.addUser(user);
            return user;
        } catch (DaoException ex) {
        	log.debug(ex.getMessage());
            throw new ServiceException("Could Not Add User", ex);
        }

    }

    public User getUserService(int userId) {
        try {
            return userDao.getUser(userId);
        } catch (DaoException ex) {
            throw new ServiceException("Could Not get User", ex);
        }
    }

    public void updateUserService(User user) {
        try {
            userDao.addUser(user);
        } catch (DaoException ex) {
        	log.debug(ex.getMessage());
            throw new ServiceException("Could Not Update User", ex);
        }

    }

    public void deleteUserService(int userId) {
        try {
            userDao.deleteUser(userId);
        } catch (DaoException ex) {
        	log.debug(ex.getMessage());
            throw new ServiceException("Could Not Delete User", ex);
        }
    }
    
    public boolean getUserExists(String username)
    {
    	HashMap<String, Integer> usernames = userDao.getUserNames();
    	return usernames.containsKey(username);
    	
    }
    
    public User getUserByUsername(String username,String password)
    {
    	HashMap<String, Integer> usernames = userDao.getUserNames();
    	if(usernames.containsKey(username))
    	{
    		int userid=usernames.get(username);
    		User user=userDao.getUser(userid);
    		if(user.getPassword().equals(password))
    			return user;
    	}
    	return null;
    		
    }
    
    

}
