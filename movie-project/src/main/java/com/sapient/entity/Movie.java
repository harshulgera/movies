package com.sapient.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    int movieId;
    String Title;
    Integer year;
    String genre;
    List<String> cast;
    List<Review> review;
}
