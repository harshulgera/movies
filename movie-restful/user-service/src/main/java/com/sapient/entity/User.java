package com.sapient.entity;


import com.sapient.vob.ReviewVob;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="USER")
public class User
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userId;
    private String name;
    private String number;
    private String email;
    private String password;
    private Double balance;

//    @OneToMany(mappedBy = "user")
//    private List<ReviewVob> review;

}
