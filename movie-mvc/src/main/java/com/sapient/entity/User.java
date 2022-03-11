package com.sapient.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

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
    @Column(unique = true)
    private String email;
    private String password;
    private Double balance;
    private Boolean isAdmin;
    
    @OneToMany(mappedBy = "user")
    private List<Review> review;

}
