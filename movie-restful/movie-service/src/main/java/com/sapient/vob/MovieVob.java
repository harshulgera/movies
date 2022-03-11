package com.sapient.vob;

import lombok.Data;

import java.util.List;

@Data
public class MovieVob
{
    private int  movieId;
    private String title;
    private int year;
    private String genre;
    private String casts;
    private List<ReviewVob> reviews;

}
