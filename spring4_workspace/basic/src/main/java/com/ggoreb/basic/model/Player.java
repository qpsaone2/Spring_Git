package com.ggoreb.basic.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	String name;
	int age;
	@ManyToOne
	Team team;
}







