package com.qconsp.cep;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qconsp.BaseTest;

public class CepAopTest extends BaseTest {

	@Autowired
	CepDao cepDao;

	Gson gson;

	@Before
	public void before() {
		gson = new GsonBuilder().create();
	}

	@Test
	public void listagemDeCepSimples() throws Exception {
		Cep find = cepDao.find("05458-002");
		out.println(gson.toJson(find));
	}


//	@Test
	public void recupera10000DoBanco() throws Exception {

		List<String> ceps = getCeps();

		Jedis jedis = new Jedis("127.0.0.1", 1200);

		System.out.println(cepDao);

		long start = currentTimeMillis();
		for (String cep : ceps) {
			Cep c = cepDao.find(cep);
			jedis.set(cep, gson.toJson(c));
		}
		out.println("Tempo Banco: " + (currentTimeMillis() - start) + "ms., lista com: " + ceps.size());

		jedis.close();
	}



}
