package com.sapient.views;

import java.util.Arrays;

import com.sapient.entity.Genre;
import com.sapient.service.MovieService;
import com.sapient.service.ReviewService;
import com.sapient.service.UserService;

public class LoadDB {

    MovieService movieService;
    UserService userService;
    ReviewService reviewService;
    public LoadDB() {
        movieService = new MovieService();
        userService= new UserService();
        reviewService= new ReviewService();
    }

    public void addData() {

    	userService.addUserService("Harshul Gera", "7388422221", "hargera", "pass", 10.0); //--> 300
    	userService.addUserService("Sachin Shukla", "8851288023", "sachin", "pass", 10.0); //--> 301
    	userService.addUserService("Parth Gupta", "7704076288", "parthg", "pass", 10.0); // --> 302
    	
    	movieService.addMovieService("Iron Man", 2008, Genre.Action, Arrays.asList("Robert Downey Jr.", "Terrence Howard")); //-->100
        movieService.addMovieService("Iron Man 2", 2010, Genre.Action, Arrays.asList("Robert Downey Jr.", "Scarlett Johansson")); //-->101
        movieService.addMovieService("Captain America", 2009, Genre.Action, Arrays.asList("Chris Evans", "Sebastian Stan")); //-->102
        
        movieService.addMovieService("Pursuit of Happyness", 2009, Genre.Drama, Arrays.asList("Will Smith", "Sebastian Stan")); //-->103
        movieService.addMovieService("Titanic",1997, Genre.Drama, Arrays.asList("Leonardo DiCaprio")); //-->104
        movieService.addMovieService("Sherlock Holmes",2016, Genre.Thriller, Arrays.asList("Benedict Cumberbatch")); //-->105
        
        //reviews by user 300
        reviewService.addReviewService( 100, 300,"Wow, Awesome !", 8.2);
        reviewService.addReviewService( 101,300, "Sequel is good, was expecting more!", 7.5);
        reviewService.addReviewService( 103,300, "Really Inspiring!", 9.7);
        reviewService.addReviewService( 105,300, "Awestruck! Best Justification to Enid Byton Stories", 9.7);
        
      //reviews by user 301
        reviewService.addReviewService(100, 301, "Time Pass!", 6.2);
        reviewService.addReviewService( 101, 301,"Waste of Money!", 5.0);
        reviewService.addReviewService( 105,301, "Great Work by Dr. Stephen Strange XD", 8.8);
        reviewService.addReviewService( 104,301, "Nice direction by James Cameron",9.0);
        
      //reviews by user 302
        reviewService.addReviewService( 100, 302,"Great Work by Robert!", 7.8);
        reviewService.addReviewService( 102, 302,"Great Work by Chris!", 8.0);
        reviewService.addReviewService( 103, 302,"Just Perfect!", 9.0);
        reviewService.addReviewService( 104, 302,"Really Nice Movie! Worth the time spend!", 9.1);
        
    }

}
