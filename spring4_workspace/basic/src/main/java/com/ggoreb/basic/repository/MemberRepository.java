package com.ggoreb.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggoreb.basic.model.Member;

public interface MemberRepository 
extends JpaRepository<Member, Long>{

}
