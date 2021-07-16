package com.ggoreb.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ggoreb.basic.model.Order;
import com.ggoreb.basic.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@Autowired
	OrderRepository or;
	
	@GetMapping("/order")
	@ResponseBody
	public List<Order> order() {
		return or.findAll();
	}
	
	@RequestMapping("/home")
	public void home() {
		log.debug("DEbug");
		log.error("error");
		System.out.println("sysout");
//		return "<u>home</u>";
	}
}





