package com.ggoreb.basic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String title;
	
	
}