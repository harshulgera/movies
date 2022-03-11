package com.sapient.dao;

import com.sapient.entity.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewDao extends CrudRepository<Review,Integer> {

    public List<Review> findByUserId(Integer id);
    public List<Review> findByMovieId(Integer id);

}
