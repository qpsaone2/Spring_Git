package com.ggoreb.hospital.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Hospital {
	@Id
	int id;
	String sido;
	String name;
	int medical;
	int room;
	String tel;
	String address;
	
}
