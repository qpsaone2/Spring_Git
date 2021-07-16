package com.ggoreb.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggoreb.basic.model.Emp;

public interface EmpRepository 
extends JpaRepository<Emp, Long>{

}
