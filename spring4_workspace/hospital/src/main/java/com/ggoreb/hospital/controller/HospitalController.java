package com.ggoreb.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ggoreb.hospital.repository.HospitalRepository;

@Controller
public class HospitalController {
	   
	   @Autowired
	   HospitalRepository h;
	   
	   @GetMapping("/hospital")
	   public String hospital(Model model) {
		   model.addAttribute("list",h.findAll());
		   return "hospital";
	   }
	   
}
