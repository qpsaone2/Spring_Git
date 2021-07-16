package com.ggoreb.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggoreb.basic.model.Order;

public interface OrderRepository 
extends JpaRepository<Order, Long>{

}
