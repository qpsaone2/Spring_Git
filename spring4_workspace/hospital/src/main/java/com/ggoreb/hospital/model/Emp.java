package com.ggoreb.hospital.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Emp {
   @Id
   int empno;
   String ename;
   String job;
   Integer deptno;
   Integer sal;
   Integer comm;
}