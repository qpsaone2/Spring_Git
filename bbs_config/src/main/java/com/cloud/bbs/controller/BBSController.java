package com.cloud.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.bbs.dto.BBSDto;
import com.cloud.bbs.service.BBSService;

@Controller
public class BBSController {
	
	@Autowired  //@Auowired는 스프링이 제공하는 애노테이션
	private BBSService bbsService;
	
	@RequestMapping("/list.bbs")
	public String list(Model model) {			
		model.addAttribute("articleList", bbsService.list());
		return "list";
	}
	
//	@RequestMapping(value="/write.bbs", method = RequestMethod.GET)
	@GetMapping("/write.bbs")
	public String writeForm(HttpSession session) {	
		return "writeForm";
	}
	
	@PostMapping("/write.bbs")	
	public String write(BBSDto article, @RequestPart("fileUpload") List<MultipartFile> fileUpload,
			HttpSession session) {	
//	public String write(@RequestParam("title") String title, @RequestParam("content") String content) {
//	public String write(@RequestParam String title, @RequestParam String content) {		
		bbsService.write(article,fileUpload);
		return "redirect:/list.bbs";
	}	
	
	@GetMapping("/login.bbs")	
	public String loginForm() {		
		return "login";
	}
	
	@PostMapping("/login.bbs")	
	public String login(@RequestParam("id") String id, 
						@RequestParam("pass") String pass, HttpSession session) {		
		return bbsService.login(id,pass,session);
	}
	
	@GetMapping("/content.bbs")
	public String content(@RequestParam("articleNum") String articleNum, Model model) {
		model.addAttribute("article", bbsService.content(articleNum));
		model.addAttribute("commentCount", bbsService.getCommentCount(articleNum));		
		model.addAttribute("fileList", bbsService.getFiles(articleNum));
		return "content";		
	}	
	
//	리턴타입이 void이면 요청 이름이 뷰이름이 됨
//	@GetMapping("/content.bbs")
//	public void content(@RequestParam("articleNum") String articleNum, Model model) {
//		model.addAttribute("article", bbsService.content(articleNum));
//		model.addAttribute("fileList", bbsService.getFiles(articleNum));				
//	}
//	
	@GetMapping("/update.bbs")
	public String updateForm(@RequestParam("articleNum") String articleNum, Model model) {
		model.addAttribute("article", bbsService.updateForm(articleNum));
		return "updateForm";		
	}
	
	@PostMapping("/update.bbs")
	public String update(BBSDto article) {
		bbsService.update(article);
		return "redirect:/content.bbs?articleNum="+article.getArticleNum();		
	}
	
	@GetMapping("/delete.bbs")
	public String delete(@RequestParam("articleNum") String articleNum) {
		bbsService.delete(articleNum);
		return "redirect:/list.bbs";
		
	}
	
	@GetMapping("/download.bbs")
	@ResponseBody
	public FileSystemResource download(@RequestParam("savedFileName") String savedFileName,
										HttpServletResponse reponse) {
		return bbsService.download(savedFileName,reponse);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
