package com.cloud.bbs.dao1;

import java.util.HashMap;
import java.util.List;

import com.cloud.bbs.dto.CommentDto;

public interface CommentDao {
	public void commentWrite(CommentDto comment); //mapper는 파라미터 하나만 받을 수 있음 
	public List<CommentDto> getComments(HashMap<String, Integer> hm);

}
