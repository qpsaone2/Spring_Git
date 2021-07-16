package com.ggoreb.basic.controller;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UploadController {
	@GetMapping("/upload1")
	public String upload1() {
		//파일 저장 위치
		//1. NAS (Network Attach Storage)
		//2. static resources folder  --> 누구나 접근이 가능하다 
		
		String path = this.getClass().getClassLoader().getResource("").getPath();
		System.out.println(path);
		return "upload1";
	}

	@PostMapping("/upload1")
	@ResponseBody
	public String upload1Post(MultipartHttpServletRequest mRequest) {
		
		Iterator<String> iter = mRequest.getFileNames();
		while (iter.hasNext()) {
			String name = iter.next();
			System.out.println(name);
			
			List<MultipartFile> mFile = mRequest.getFiles(name);
			for(MultipartFile f : mFile) {
				long time = System.currentTimeMillis();
				String fName = f.getOriginalFilename();
				//String -> substring , replace, indexOf, split 문자열을 변형시키는것 ( 외우기 )
				System.out.println(fName);
				String prefix = fName.substring(0,fName.indexOf("."));
				String suffix = fName.substring(fName.indexOf("."));
				fName= prefix + "_" + time + suffix;
			
				try {
					//d:/work/basic
					String aPath = new File("").getAbsolutePath();
					//       +        /src/main/static + fName
					f.transferTo(new File(aPath+ "/target/m2e-wtp/web-resources/" + fName)); //현재경로에 저장된다 fName만있다면                                                
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //file upload 
				System.out.println(fName);
			}
		}
		
//		String result = "";
//		MultipartFile mFile = mRequest.getFile("file");
//		String oName = mFile.getOriginalFilename();
//		result += oName + "\n";
		return "아무거나";
	}
}