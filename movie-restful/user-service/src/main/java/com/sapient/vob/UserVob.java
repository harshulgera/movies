package com.sapient.vob;

import lombok.Data;

import java.util.List;

@Data
public class UserVob
{
    private Integer userId;
    private String name;
    private String number;
    private String email;
    private String password;
    private Double balance;
    private List<ReviewVob> reviews;


}
