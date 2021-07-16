package com.ggoreb.basic.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Order {
	@Id
	Integer id;
	String name;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	Customer customer;
}








