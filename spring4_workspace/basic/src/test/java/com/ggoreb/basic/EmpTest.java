package com.ggoreb.basic;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ggoreb.basic.model.Emp;
import com.ggoreb.basic.repository.EmpRepository;

@SpringBootTest
class EmpTest {
	@Autowired
	EmpRepository empRepository;
	
	@Test
	public void test() {
		List<Emp> list = empRepository.findAll();
		System.out.println(list);
		
		System.out.println(list.get(0).getDept());
	}
	
}





