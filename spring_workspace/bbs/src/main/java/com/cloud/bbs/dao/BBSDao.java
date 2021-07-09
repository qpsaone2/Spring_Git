package com.cloud.bbs.dao;

import java.util.List;

import com.cloud.bbs.dto.BBSDto;
import com.cloud.bbs.dto.FileDto;

public interface BBSDao {
	public List<BBSDto> list();
	public String login(String id);
	public void write(BBSDto article);
	public BBSDto content(String articleNum);
	public int getCommentCount(String articleNum);
	public List<FileDto> getFiles(String articleNum);
	public BBSDto updateForm(String articleNum);
	public void update(BBSDto article);
	public void delete(String articleNum);
	public void insertFile(FileDto fileDto);
	public String getOriginalFileName(String savedFileName);
	public int getTotalCount();
}
