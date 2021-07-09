package com.cloud.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.bbs.dto.CommentDto;
import com.cloud.bbs.service.CommentService;

//@Controller
@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/commentWrite.comment")
//	@ResponseBody
	public List<CommentDto> commentWrite(CommentDto comment, HttpSession session) {
		comment.setId((String)session.getAttribute("id"));
		return commentService.commentWrite(comment);
	}
	
	@PostMapping("/commentRead.comment")
//	@ResponseBody
	public List<CommentDto> commentRead(@RequestParam("articleNum") int articleNum,
										@RequestParam("commentRow") int commentRow) {		
		return commentService.commentRead(articleNum, commentRow);
	}
	
	
	
	
	
	
	
	
	
	
	
 
}
