package com.sapient.dao;

import org.springframework.transaction.annotation.Transactional;

import com.sapient.entity.User;
import com.sapient.exception.DaoException;

public interface UserDao {

    // CRUD
	@Transactional
    public User addUser(User user) throws DaoException;

    public User getUser(int userId) throws DaoException;

    @Transactional
    public User updateUser(User user) throws DaoException;

}