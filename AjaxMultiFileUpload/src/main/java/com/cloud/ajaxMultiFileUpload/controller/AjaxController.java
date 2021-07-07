package com.cloud.ajaxMultiFileUpload.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.ajaxMultiFileUpload.common.MediaUtils;
import com.cloud.ajaxMultiFileUpload.common.UploadFileUtils;
import com.cloud.ajaxMultiFileUpload.service.AjaxService;

@Controller
public class AjaxController {
	
	@Resource(name="saveDir")
	String saveDir;
	
	@Autowired
	private AjaxService ajaxService;
	
	
	@GetMapping("/main.bbs")
	public String main() {
		return "uploadAjax";
	}
	
	@PostMapping("/uploadAjax.bbs")
	@ResponseBody
	public List<String> uploadAjax(@RequestPart("multiFile") List<MultipartFile> mFile) throws Exception {
		
		List<String> fileList= new ArrayList<>();
		
		for(MultipartFile file : mFile){			
			fileList.add(UploadFileUtils.uploadFile(saveDir,file.getOriginalFilename(),file.getBytes()));
		}
		return fileList;
	}
	
	
	@ResponseBody
	@GetMapping("/displayFile.bbs")
	public ResponseEntity<byte[]>  displayFile(@RequestParam("fileName") String fileName)	throws Exception{
		System.out.println(fileName);
		InputStream in = null; 
		ResponseEntity<byte[]> entity = null;
		try{      
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1); //lastIndexOf 뒤에서 부터 찾아서 처음 나타나는 . 을찾는다 그뒤  +1 
			MediaType mType = MediaUtils.getMediaType(formatName);      
			HttpHeaders headers = new HttpHeaders();      
			in = new FileInputStream(saveDir+fileName);	 //s_가 붙은 썸네일 이미지 넘어옴
			if(mType != null) {
				headers.setContentType(mType); //이미지 다운도르 알려줌
			}else{			      
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);			//일반다운로드 헤더에 알렴줌
			}			
			fileName = fileName.substring(fileName.indexOf("_")+1);  //앞에서부터 읽으면서 _가 나타남,첫번쨰 _가있는 뒤를 다 잘라냄
			headers.add("Content-Disposition","attachment; filename=\""+ 
					URLEncoder.encode(fileName,"utf-8").replace("+","%20")+"\"");
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(
					HttpStatus.BAD_REQUEST);
		}finally{
			in.close();
		}
		return entity;    
	}
	
	@ResponseBody
	@PostMapping(value = "/deleteFile.bbs")
	public ResponseEntity<String> deleteFile(String fileName){
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		new File(saveDir + fileName.replace('/',File.separatorChar)).delete();
		
		if(mType !=null) {
			String front = fileName.substring(0,12);
			String end = fileName.substring(14);
			new File(saveDir + (front+end).replace('/',File.separatorChar)).delete();
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
}
