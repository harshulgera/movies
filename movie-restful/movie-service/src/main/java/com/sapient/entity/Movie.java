package com.sapient.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="MOVIES")
public class Movie {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int  movieId;
    private String title;
    private int year;
    private String genre;
    private String casts;


//    @OneToMany(mappedBy = "movie")
//    List<Review> reviews;



}
