package com.ggoreb.basic.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Emp {
	@Id
	Integer empno;
	String ename;
	String job;
	Long mgr;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "deptno")
	Dept dept;  // dept_deptno

//	@Override
//	public String toString() {
//		return 
//				"Emp [empno=" + empno + ", ename=" + ename + 
//				", job=" + job + ", mgr=" + mgr + "]";
//	}
//	
	
}








