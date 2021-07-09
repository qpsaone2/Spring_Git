package com.cloud.bbs;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cloud.bbs.dao.BBSDao;



@RunWith(SpringJUnit4ClassRunner.class)
//스프링 4 부터는 클래스 베이스의 테스트도 가능함
//Spring 4.0.4, context loaders may choose to support path-based and 
//				class-based resources simultaneously
//이렇게도 사용 가능 @ContextConfiguration(classes = {AppConfig.class})
//@WebAppConfiguration WAS없이 테스트 할테 꼭 필요한 애노테이션임
@WebAppConfiguration
//@ContextConfiguration(locations={"classpath:/root-context.xml","classpath:/bbsServlet-context.xml"})
//@ContextConfiguration(locations={"classpath:/springConfig/*.xml"})
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/bbsServlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})

public class TestController { 
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private BBSDao bbsDao;
	
	@Before
	public void beforeGetTotalCount() {
		logger.info("@Test메소드 이전에 실행됩니다");
	}
	
	@Test
	public void  writeTest() {
		logger.info("총글의 갯수는 "+bbsDao.getTotalCount());
	}

}
