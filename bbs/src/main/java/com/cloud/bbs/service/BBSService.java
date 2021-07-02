package com.cloud.bbs.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.bbs.dto.BBSDto;
import com.cloud.bbs.dto.FileDto;

public interface BBSService {
	public List<BBSDto> list();
	   public String login(String id, String pass, HttpSession session);
	   public void write(BBSDto article, List<MultipartFile> fileUpload);
	   public BBSDto content(String articleNum);
	   public int getCommentCount(String articleNum);
	   public List<FileDto> getFiles(String articleNum);
	   public BBSDto updateForm(String articleNum);
	   public void update(BBSDto article);
	   public void delete(String articleNum);
	   public FileSystemResource download(String savedFileName, HttpServletResponse response);
}