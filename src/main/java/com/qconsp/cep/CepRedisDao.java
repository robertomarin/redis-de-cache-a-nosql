package com.qconsp.cep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Repository
public class CepRedisDao {
	
	@Autowired
	private CepDao cepDao;

	private Jedis jedis;

	private Gson gson;
	
	public CepRedisDao() {
		jedis = new Jedis("127.0.0.1", 1200);
		gson = new GsonBuilder().create();
	}

	public Cep find(String cepString) {
		String cepJson = jedis.get(cepString);

		if (cepJson != null) {
			Cep cep = gson.fromJson(cepJson, Cep.class);

			if (cep != null) {
				System.out.println("### Recuperado pelo REDIS!");
				return cep;
			}
		}

		Cep cep = cepDao.find(cepString);

		System.out.println("### Recuperado pelo BANCO!");
		
		jedis.setex(cepString, 60 * 10, gson.toJson(cep));

		return cep;
	}

}
