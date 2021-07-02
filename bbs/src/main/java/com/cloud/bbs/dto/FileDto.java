package com.cloud.bbs.dto;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class FileDto {
	private int fileNum;
	private String originalFileName;
	private String savedFileName;
	private int articleNum;
	
}
