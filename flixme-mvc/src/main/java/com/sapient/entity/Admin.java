package com.sapient.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ADMIN")
public class Admin
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer adminId;
    private String name;
    private String number;

}