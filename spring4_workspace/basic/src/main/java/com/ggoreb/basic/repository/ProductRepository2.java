package com.ggoreb.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ggoreb.basic.model.Product2;

@Repository
public interface ProductRepository2 
	extends JpaRepository<Product2, Long> {
	
	
}