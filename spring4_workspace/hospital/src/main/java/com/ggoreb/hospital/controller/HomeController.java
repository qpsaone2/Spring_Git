package com.ggoreb.hospital.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ggoreb.hospital.model.Emp;
import com.ggoreb.hospital.repository.EmpRepository;

@Controller
public class HomeController {
   
   @GetMapping("/html")
   public String html(
		   @RequestParam(value="area", defaultValue="") String area, Model model) {
      model.addAttribute("area", area);
      return "home";
   }
  
 
   
   @GetMapping("/json")
   @ResponseBody
   public Map<String, String> json(
		   @RequestParam(value="area", defaultValue="") String area) {
	   Map<String, String> map = new HashMap<>();
	   map.put("area",area);
      return map;
   }
   
   
   @Autowired
   EmpRepository e;
   
   @GetMapping("/emp/{sal}") // /{sal} = PathVariable
   @ResponseBody
   public List<Emp> empSal(@PathVariable("sal")int sal) {
	   return e.findAllBySalGreaterThanEqual(sal);
   }
   
   @GetMapping("/emp/deptno")
   @ResponseBody
   public List<Emp> empDeptno(int deptno) {
	   return e.findAllByDeptno(deptno);
   }
   
   @GetMapping("/emp/ename")
   @ResponseBody
   public List<Emp> empEname(String ename) {
	   return e.findAllByEnameContainingOrderByEname(ename);
   }
   
   @GetMapping("/emp")
   @ResponseBody
   public List<Emp> emp() {
       return e.findAll();
   }

   
   
}