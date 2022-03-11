package com.sapient.controller;

import com.sapient.dao.ReviewDao;
import com.sapient.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController
{

    @Autowired
    ReviewDao dao;

    @GetMapping({"/{id}"})
    public ResponseEntity<Object> getOne(@PathVariable Integer id){
        Optional<Review> result = dao.findById(id);
        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for id " + id);
    }

    @GetMapping({"/user/{id}"})
    public List<Review> getReviewsByUserId(@PathVariable Integer id){
        List<Review> reviews=dao.findByUserId(id);
        return reviews;
    }

    @GetMapping({"/movie/{id}"})
    public List<Review> getReviewsByMovie(@PathVariable Integer id){
        List<Review> reviews=dao.findByMovieId(id);
        return reviews;
    }

    @GetMapping
    public Iterable<Review> getAll(){
        return dao.findAll();
    }




}
