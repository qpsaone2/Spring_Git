package com.ggoreb.basic;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//class MyExcept extends RuntimeException {
//	
//}
//
//class MyExcept2 extends Exception {
//	
//}

@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) {
//		int[] arr = new int[2];
//		System.out.println(arr[5]);
//		
//		File f = new File("");
//		try {
//			f.createNewFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		SpringApplication.run(BasicApplication.class, args);
	}

}
