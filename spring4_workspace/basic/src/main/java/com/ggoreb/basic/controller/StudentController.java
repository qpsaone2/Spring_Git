package com.ggoreb.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ggoreb.basic.model.Student;
import com.ggoreb.basic.repository.StudentRepository;

@Controller
public class StudentController {
	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/student")
	public String student() {
		return "student";
	}
	
	@PostMapping("/student")
	public String studentPost(@ModelAttribute Student student) {
		// 관련 데이터를 DB에 저장 또는 수정 또는 삭제
		studentRepository.save(student);
		return "redirect:/main";
	}
}








