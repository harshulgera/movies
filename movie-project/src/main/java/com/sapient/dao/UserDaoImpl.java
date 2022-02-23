package com.sapient.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sapient.entity.Review;
import com.sapient.entity.User;
import com.sapient.exception.DaoException;

public class UserDaoImpl implements UserDao {

    private static HashMap<Integer, User> users = new HashMap<>();
    private static HashMap<String, Integer> userNames = new HashMap<>();
    static int cnt = 300;

    @Override
    public User addUser(User user) throws DaoException {
        user.setUserId(cnt);
        userNames.put(user.getUsername(),cnt);
        users.put(cnt++, user);
        return user;
    }

    @Override
    public User getUser(int userId) throws DaoException {
        return users.get(userId);
    }

    @Override
    public void updateUser(User user) throws DaoException {
        users.put(user.getUserId(), user);
    }

    @Override
    public void deleteUser(int userId) throws DaoException {
        users.remove(userId);
    }
    
    public HashMap<String,Integer> getUserNames() throws DaoException
    {
    	return userNames;
    }

	@Override
	public void addReview(Review review) throws DaoException {
		User user= getUser(review.getUserId());
		List<Review> reviews= user.getReview();
		if(reviews==null)
			reviews= new ArrayList<>();
		
		reviews.add(review);
	}

	@Override
	public void deleteReview(Review review) throws DaoException {
		User user= getUser(review.getUserId());
		List<Review> reviews= user.getReview();
		reviews.remove(review);		
	}

}
