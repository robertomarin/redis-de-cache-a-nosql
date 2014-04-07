package com.qconsp;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class Redis01 {

	private Jedis jedis;

	@Before
	public void before() {
		jedis = new Jedis("127.0.0.1", 1200);
	}

	@Test
	public void redis() throws Exception {
		jedis.set("qconsp", "Olá QCON! adeus...");

		jedis.expire("qconsp", 2);
		
		System.out.println(jedis.get("qconsp"));
		
		Thread.sleep(3000);
		
		System.out.println(jedis.get("qconsp"));
		
	}
}
