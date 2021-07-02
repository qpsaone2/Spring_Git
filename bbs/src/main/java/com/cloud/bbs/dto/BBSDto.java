package com.cloud.bbs.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
@ToString
public class BBSDto {

	private int articleNum;
	private String id;
	private String title;
	private String content;
	private Timestamp writeDate;
	
}
