package com.ggoreb.practice.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ggoreb.practice.model.Answer;
import com.ggoreb.practice.model.Question;
import com.ggoreb.practice.model.User;
import com.ggoreb.practice.repository.AnswerRepository;
import com.ggoreb.practice.repository.QuestionRepository;

@Controller
public class AnswerController {
	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@PostMapping("/answer/create")
	public String answerCreate(
			@ModelAttribute Answer answer, HttpSession session, @RequestParam("q_id") Long q_id) {
		Optional<Question> opt = questionRepository.findById(q_id);
		Question question = opt.get();
		
		Object obj = session.getAttribute("user");
		User user = (User) obj;
		answer.setUser(user);  
		answer.setQuestion(question);
		
		answerRepository.save(answer);
		return "redirect:/question/list";
	}
	
}
