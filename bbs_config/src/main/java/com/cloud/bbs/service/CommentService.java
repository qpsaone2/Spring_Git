package com.cloud.bbs.service;

import java.util.List;

import com.cloud.bbs.dto.CommentDto;

public interface CommentService {
	public List<CommentDto> commentWrite(CommentDto comment);
	public List<CommentDto> commentRead(int articleNum, int commentRow);

}
