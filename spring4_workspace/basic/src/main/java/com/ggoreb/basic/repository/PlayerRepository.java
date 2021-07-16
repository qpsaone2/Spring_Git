package com.ggoreb.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggoreb.basic.model.Player;

public interface PlayerRepository 
extends JpaRepository<Player, Long>{

}
