package com.qconsp;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class Redis02 {

	private Jedis jedis;

	@Before
	public void before() {
		jedis = new Jedis("127.0.0.1", 1200);
	}

	@Test
	public void redis() throws Exception {
		
		long start = currentTimeMillis();
		
		for (int i = 0; i < 10000; i++) {
			jedis.set("key-" + i, "algum valor: " + i);
		}
		
		
		System.out.println("Time: " + (currentTimeMillis() - start) + "ms.");
		
		
	}
	
}
