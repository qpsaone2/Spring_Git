package com.ggoreb.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggoreb.basic.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
