package com.cloud.bbs.service;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.bbs.common.FileSaveHelper;
import com.cloud.bbs.dao.BBSDao;
import com.cloud.bbs.dto.BBSDto;
import com.cloud.bbs.dto.FileDto;

@Service
public class BBSServiceimpl implements BBSService {
   
   @Autowired
   private BBSDao bbsDao;

   @Autowired
   private FileSaveHelper fileSaveHelper;
   
   @Resource(name="saveDir")
   private String saveDir;
   
   @Override
   public List<BBSDto> list() {
      return bbsDao.list();
   }

   @Override
   public String login(String id, String pass, HttpSession session) {
	String dbpass = bbsDao.login(id);
	
	if(dbpass ==null) {
		System.out.println("회원아님");
		return null;
	}
	else if(dbpass.equals(pass)){
		session.setAttribute("id",id);
		
		//redirect:가 붙으면 다시 서버에 요청을 한다.
		return "redirect:/list.bbs";
	}
	else {
		return "login";
	}
	
}
   @Transactional()
   @Override
   public void write(BBSDto article, List<MultipartFile> fileUpload) {
      System.out.println(article.getArticleNum());
      bbsDao.write(article);
      System.out.println(article.getArticleNum());
      if(!fileUpload.get(0).isEmpty()) {
         for(MultipartFile mf : fileUpload) {
            String savedFileName=fileSaveHelper.save(mf);
            FileDto fileDto = new FileDto();
            fileDto.setOriginalFileName(mf.getOriginalFilename());
            fileDto.setSavedFileName(savedFileName);
            fileDto.setArticleNum(article.getArticleNum());
            bbsDao.insertFile(fileDto);
         }
      }
   }
   @Override
   public BBSDto content(String articleNum) {
	
	return bbsDao.content(articleNum);
	
}
   
   @Override
   public int getCommentCount(String articleNum) {
	   return bbsDao.getCommentCount(articleNum);
   }
   
   @Override
   public FileSystemResource download(String savedFileName, HttpServletResponse response) {
      response.setContentType("application/download");
      String originalFileName=bbsDao.getOriginalFileName(savedFileName);
      try {
         originalFileName = URLEncoder.encode(originalFileName,"utf-8").replace("+","%20").replace("%28","(").replace("%29", ")");
      } catch(Exception e) {
         
      }
      
      response.setHeader("Content-Disposition", "attachment;" + " filename=\""+originalFileName+"\";");
      FileSystemResource fsr = new FileSystemResource(saveDir+savedFileName);
      return fsr;
   }

@Override
public List<FileDto> getFiles(String articleNum) {
		   return bbsDao.getFiles(articleNum);
}

@Override
   public BBSDto updateForm(String articleNum) {
	   return bbsDao.updateForm(articleNum);
   }

   @Override
   public void update(BBSDto article) {
	   bbsDao.update(article);
	
   }

   @Override
   public void delete(String articleNum) {
	   bbsDao.delete(articleNum);
	
   }


   

}