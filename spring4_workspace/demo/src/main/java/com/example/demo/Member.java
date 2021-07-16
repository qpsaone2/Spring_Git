package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Member {
	@Id
	int id;
	String name;
	int age;
	
}
