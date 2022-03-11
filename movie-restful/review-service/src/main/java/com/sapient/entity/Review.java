package com.sapient.entity;


import com.sapient.vob.UserVob;
import lombok.Data;

import javax.persistence.*;

@Data
//@ToString(exclude = {"movie","user"})
@Entity
@Table(name="REVIEWS")
public class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int reviewId;
    private String views;
    private Double rating;

    @Column(name="user_userId")
    private Integer userId;

    @Column(name="movie_movieId")
    private Integer movieId;

}