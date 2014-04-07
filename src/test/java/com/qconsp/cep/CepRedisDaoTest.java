package com.qconsp.cep;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qconsp.BaseTest;

public class CepRedisDaoTest extends BaseTest {

	@Autowired
	private CepRedisDao cepRedisDao;
	
	private Gson gson;

	@Before
	public void before() {
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	@Test
	public void test() {
		Cep cep = cepRedisDao.find("05458-000");
		
		System.out.println(gson.toJson(cep));
	}

}
