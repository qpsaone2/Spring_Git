package com.cloud.bbs.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@PropertySource("classpath:/db.properties")
@EnableTransactionManagement// 어노테이션 기반 트랜잭션 관리 사용
@MapperScan(basePackages = {"com.cloud.bbs.dao"})
public class RootConfig {

	@Autowired
	ApplicationContext applicationContext;

	@Value("oracle.jdbc.driver.OracleDriver")
	private String driver;
	@Value("jdbc:oracle:thin:@localhost")
	private String url;
	@Value("human")
	private String username;
	@Value("1234")
	private String password;

	//	@Value("${jdbc.driver}")
	//    private String driver;
	//    @Value("${jdbc.url}")
	//    private String url;
	//    @Value("${jdbc.username")
	//    private String username;
	//    @Value("${jdbc.password")
	//    private String password;

	@Bean
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		return basicDataSource;
	}
	
//	@Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//        dataSource.setUrl("jdbc:oracle:thin:@localhost");
//        dataSource.setUsername("human");
//        dataSource.setPassword("1234");
//        return dataSource;
//    }

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource());
		return dataSourceTransactionManager;
	}
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setConfigLocation(applicationContext
				.getResource("classpath:/mybatisConfig/mybatisConfig.xml"));
		factoryBean.setMapperLocations(applicationContext
				.getResources("classpath:/com/cloud/mapper/**/*.xml"));
		return factoryBean;
//		return factoryBean.getObject();
	}
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	@Bean
	public String saveDir() {		
		return new String("d:/upload/");
	}
	
	

	    

	  
	   
	  

	
	 

	

}
