package com.sapient.dao;

import com.sapient.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<User,Integer> {

    public Optional<User> findByEmail(String email);
}
