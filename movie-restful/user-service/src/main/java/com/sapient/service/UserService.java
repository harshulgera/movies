package com.sapient.service;

import com.sapient.dao.UserDao;
import com.sapient.entity.User;
import com.sapient.vob.ReviewVob;
import com.sapient.vob.UserVob;
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
public class UserService
{

    @Autowired
    UserDao userDao;

    @Value("${review.service.url}")
    String reviewServiceUrl;

    public UserVob toUserVob(User user) {
        UserVob userVob= new UserVob();
        userVob.setUserId(user.getUserId());
        userVob.setName(user.getName());
        userVob.setNumber(user.getNumber());
        userVob.setEmail(user.getEmail());
        userVob.setPassword(user.getPassword());
        userVob.setBalance(user.getBalance());
        userVob.setReviews(toListReviewVob(user.getUserId()));

      return userVob;
    }


    public UserVob getUserById(Integer id)
    {
        Optional<User> result = userDao.findById(id);
        if (result.isPresent()) {
            UserVob userVob = toUserVob(result.get());
            return userVob;
        }
        return null;
    }

    public List<ReviewVob> getReviewsByMovieId(Integer movieId)
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
            log.warn("There was an error in EmployeeController.toDepartmentVob()", e);
            return null;
        }

    }

    public List<ReviewVob> toListReviewVob(int userId)
    {
        try {
            ReviewVob[] reviewsVob= WebClient.create(reviewServiceUrl)
                    .get()
                    .uri("/user/" + userId)
                    .retrieve()
                    .bodyToMono(ReviewVob[].class)
                    .block();

            return Arrays.asList(reviewsVob);
        } catch (Exception e) {
            log.warn("There was an error in EmployeeController.toDepartmentVob()", e);
            return null;
        }
    }

    public User login(String email, String password) {
        Optional<User> result = userDao.findByEmail(email);
        if(result.isPresent()){
            User user= result.get();
            if(user.getPassword().equals(password)){
                return user;
            }
        }
        throw new RuntimeException("Invalid username/password");

    }



}

