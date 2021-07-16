package com.ggoreb.basic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	String name;
	
	@OneToMany
	List<Player> players = new ArrayList<>();
}



