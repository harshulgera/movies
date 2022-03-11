package com.sapient.controller;

import com.sapient.dao.MovieDao;
import com.sapient.entity.Movie;
import com.sapient.service.MovieService;
import com.sapient.vob.MovieVob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController
{

    @Autowired
    MovieDao dao;

    @Autowired
    MovieService service;

    @GetMapping({"/{id}"})
    public ResponseEntity<Object> getOne(@PathVariable Integer id){
        MovieVob movieVob=service.getMovieById(id);
        if(movieVob!=null){
            return ResponseEntity.ok(movieVob);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for id " + id);
    }

    @GetMapping
    public Iterable<Movie> getAll(){
        return dao.findAll();
    }




}
