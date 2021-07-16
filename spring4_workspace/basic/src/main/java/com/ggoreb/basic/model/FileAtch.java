package com.ggoreb.basic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class FileAtch {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String saveName;
	String originName;
	
	
	@ManyToOne
	Board board;
	
}