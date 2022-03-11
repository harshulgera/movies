package com.sapient.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapient.entity.Review;
import com.sapient.entity.User;
import com.sapient.exception.DaoException;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	HibernateTemplate template;
	
	@Override
	public User addUser(User user) throws DaoException {
		try {
			template.persist(user);
			user.setUserId(user.getUserId());
			return user;
		} catch (DataAccessException e) {
			throw new DaoException("Could Not Add User",e);
		}
	}

	@Override
	public User getUser(int userId) throws DaoException {
		try {
			return template.get(User.class, userId);
		} catch (DataAccessException e) {
			throw new DaoException("Could Not Get User",e);
		}
	}

	@Override
	public User updateUser(User user) throws DaoException {
		try {
			return template.merge(user);
		} catch (DataAccessException e) {
			throw new DaoException("Could Not Get User",e);
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public User getUserByMail(String email) throws DaoException {
		try {
		return (User) template.findByNamedParam("from User where email= :email","email", email).get(0);
	}
		catch(DaoException e)
		{
			throw new DaoException("Invalid Email Id/Password",e);
		}
	}

	
}
