package com.cloud.bbs.dao1;

import java.util.HashMap;
import java.util.List;

import com.cloud.bbs.dto.CommentDto;

public interface CommentDao {
	public void commentWrite(CommentDto comment); //mapper�� �Ķ���� �ϳ��� ���� �� ���� 
	public List<CommentDto> getComments(HashMap<String, Integer> hm);

}
