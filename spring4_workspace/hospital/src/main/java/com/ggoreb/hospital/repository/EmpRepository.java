package com.ggoreb.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ggoreb.hospital.model.Emp;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Long>{

	List<Emp> findAllBySalGreaterThanEqual(int sal);
	List<Emp> findAllByDeptno(int deptno);
	List<Emp> findAllByEnameContainingOrderByEname(String ename);

}
