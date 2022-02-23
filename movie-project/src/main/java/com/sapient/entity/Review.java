package com.sapient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    Integer reviewId;
    Integer movieId;
    Integer userId;
    String review;
    Double rating;

}
