package com.ggoreb.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggoreb.hospital.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
   //Integer는 ID가 프라이머리키니까 ID의 타입형태를 쓴다
}
