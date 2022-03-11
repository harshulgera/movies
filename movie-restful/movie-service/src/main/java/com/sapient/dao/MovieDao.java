package com.sapient.dao;

import com.sapient.entity.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieDao extends CrudRepository<Movie,Integer>
{

}
