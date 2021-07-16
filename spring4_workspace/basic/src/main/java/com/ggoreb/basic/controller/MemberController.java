package com.ggoreb.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ggoreb.basic.model.Member;
import com.ggoreb.basic.repository.MemberRepository;

@Controller
public class MemberController {
	@Autowired
	MemberRepository memberRepository;
	
	@GetMapping("/member")
	public String member() {
		return "member";
	}

	@PostMapping("/member")
	public String memberPost(@ModelAttribute Member member) {
		memberRepository.save(member);
		return "redirect:/main";
	}
}








