package com.ggoreb.basic.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Customer {
	@Id
	Integer id;
	String name;
	Integer age;
}