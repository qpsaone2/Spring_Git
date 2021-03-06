package com.ggoreb.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ggoreb.basic.model.Board;
import com.ggoreb.basic.model.FileAtch;
import com.ggoreb.basic.repository.BoardRepository;
import com.ggoreb.basic.repository.FileAtchRepository;

@Controller
public class BoardController {
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	FileAtchRepository fileAtchRepository;

		@GetMapping("/write")
		public String write() {
			return "write";
		}
		
		@PostMapping("/write")																			
		@ResponseBody
		public FileAtch writePost(@ModelAttribute Board board,
				MultipartFile aaaa) {
			Board dbBoard = boardRepository.save(board);
			
			FileAtch fileAtch = new FileAtch();
			fileAtch.setBoard(dbBoard);
			fileAtch.setOriginName(aaaa.getOriginalFilename());
			fileAtch.setSaveName(aaaa.getOriginalFilename());

			FileAtch result = fileAtchRepository.save(fileAtch);
			return result;
		}
}
