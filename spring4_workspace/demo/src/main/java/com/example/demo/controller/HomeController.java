package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Member;
import com.example.demo.MemberRepository;



@RestController
public class HomeController {

	@Autowired
	MemberRepository mr; //DAO와 같은 역할
	
	@GetMapping("/")
	public String index() {
		System.out.println(mr.findAll());
		
		Member m = new Member();
		m.setAge(10);
		m.setName("park");
		return "ok";
	}
	@GetMapping("/json")
	public Map<String, Object> json() {
		Map<String,Object> map = new HashMap<>();
		map.put("name", "home");
		map.put("age", 30);
		return map;
	}
	
}
