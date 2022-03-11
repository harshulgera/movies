package com.sapient.vob;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ReviewVob
{
    private int reviewId;
    private String views;
    private Double rating;
    @Column(name = "user_userId")
    private Integer userId;
}
