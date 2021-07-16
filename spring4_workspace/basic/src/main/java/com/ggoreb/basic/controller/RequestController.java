package com.ggoreb.basic.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.ggoreb.basic.model.User;

@Controller
public class RequestController {
	@GetMapping("req/http")
	public String http(HttpServletRequest request) {
		String name = request.getParameter("name");
		String pageNum = request.getParameter("pageNum");
		return name + ", " + pageNum;
	}

	@GetMapping("req/param1")
	public String param1(@RequestParam("key1") String key1, @RequestParam("key2") String key2) {
		return key1 + ", " + key2;
	}

	@GetMapping("req/param2")
	public String param2(
			@RequestParam Map<String, Object> map,
			Model model) {
//		model.addAttribute("map2", map);
		model.addAttribute("id", map.get("id"));
		model.addAttribute("xy", map.get("xy"));
		
		return "param2";
	}
	
	
	@GetMapping("/signUp")
	public String signUp(@ModelAttribute User user) {
		return "signUp";
	}
}






