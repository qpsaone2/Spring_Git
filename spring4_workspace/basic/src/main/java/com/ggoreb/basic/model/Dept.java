package com.ggoreb.basic.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Dept {
	@Id
	Integer deptno;
	String dname;
	String loc;
}