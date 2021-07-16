package com.ggoreb.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ggoreb.basic.model.Emp;
import com.ggoreb.basic.repository.EmpRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EmpController {
	@Autowired
	EmpRepository er;
	
	@RequestMapping("/emp")
	@ResponseBody
	public List<Emp> emp() {
		return er.findAll();
	}
}





