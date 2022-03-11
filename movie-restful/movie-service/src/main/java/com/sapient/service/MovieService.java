package com.sapient.service;

import com.sapient.dao.MovieDao;
import com.sapient.entity.Movie;
import com.sapient.vob.MovieVob;
import com.sapient.vob.ReviewVob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MovieService
{
    @Autowired
    MovieDao movieDao;

    @Value("${review.service.url}")
    String reviewServiceUrl;

    public MovieVob getMovieById(Integer id)
    {

        Optional<Movie> movie= movieDao.findById(id);
        if(movie.isPresent())
            return toMovieVob(movie.get());
        return null;
    }


    public MovieVob toMovieVob(Movie movie)
    {
        MovieVob movieVob= new MovieVob();
        movieVob.setMovieId(movie.getMovieId());
        movieVob.setTitle(movie.getTitle());
        movieVob.setYear(movie.getYear());
        movieVob.setGenre(movie.getGenre());
        movieVob.setCasts(movie.getCasts());
        movieVob.setReviews(this.toReviewVobList(movie.getMovieId()));
        return movieVob;
    }

    public List<ReviewVob> toReviewVobList(Integer movieId)
    {
        try {
            ReviewVob[] reviewsVob= WebClient.create(reviewServiceUrl)
                    .get()
                    .uri("/movie/" + movieId)
                    .retrieve()
                    .bodyToMono(ReviewVob[].class)
                    .block();

            return Arrays.asList(reviewsVob);
        } catch (Exception e) {
            log.warn("There was an error in ReviewService.toReviewVobList()", e);
            return null;
        }

    }

}
