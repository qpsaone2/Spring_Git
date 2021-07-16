package com.ggoreb.basic;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ggoreb.basic.model.Player;
import com.ggoreb.basic.model.Product;
import com.ggoreb.basic.model.Team;
import com.ggoreb.basic.repository.PlayerRepository;
import com.ggoreb.basic.repository.ProductRepository;
import com.ggoreb.basic.repository.TeamRepository;

@SpringBootTest
class ProductRepositoryTest {
	@Autowired
	TeamRepository tr;
	@Autowired
	PlayerRepository pr;
	
	@Test
	public void test4() {
		List<Player> list = pr.findAll();
		for(Player p : list) {
			p.getTeam().getId();
			p.getTeam().getName();
		}
		
		Optional<Player> opt = pr.findById(8L);
		
		Player p = opt.get();  // 조회결과가 없다면 예외 발생
		
		p.getTeam();
		
		
		
		System.out.println(p.getTeam().getName());
	}
	
	
	@Test
	public void test3() {
		Optional<Team> t = tr.findById(7L);
		Team t2 = t.get();
		
		Player p = new Player();
		p.setName("Choi");
		p.setTeam(t2);
		pr.save(p);
	}
	
	@Test
	public void test2() {
		Team t = new Team();
		tr.save(t);
		
		Player p = new Player();
		p.setName("Hong");
		p.setTeam(t);
		pr.save(p);
	}
	
	
	
	
	@Autowired
	ProductRepository p;
	
	@Test
	void getProductList() {
//		System.out.println(111);
		System.out.println(p.findAll());
	}

	@Test
	void addProduct() {
		Product product = new Product();
		product.setName("kk");
		product.setCount(3);
		product.setPrice(7000);
		p.save(product);
	}
	
}





