package com.ggoreb.practice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String subject;
	String content;

	@Temporal(TemporalType.TIMESTAMP)
	Date createDate;
	
	@OneToMany(mappedBy="question")  
	//나랑 연결되어있는 녀석이 나를 어떤 변수로 쓰고있는지 question (Answer의 엔티티에 question 변수명을 가지고온다)
	List<Answer> answerList = new ArrayList<>();
	
	@ManyToOne
	User user;
}
