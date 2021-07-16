package com.ggoreb.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggoreb.basic.model.Board;

public interface BoardRepository 
extends JpaRepository<Board, Long>{

}
