package com.sapient.dao;

import java.util.HashMap;

import com.sapient.entity.Review;
import com.sapient.entity.User;
import com.sapient.exception.DaoException;

public interface UserDao {

    // CRUD
    public User addUser(User user) throws DaoException;

    public User getUser(int userId) throws DaoException;

    public void updateUser(User user) throws DaoException;

    public void deleteUser(int userId) throws DaoException;

	public HashMap<String, Integer> getUserNames() throws DaoException;

	public void addReview(Review review) throws DaoException;
	
	public void deleteReview(Review review) throws DaoException;
}
