package com.qconsp;

import static java.lang.System.currentTimeMillis;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;

public class Redis03Sharding {

	private ShardedJedisPool pool;

	@Before
	public void before() {
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		
		shards.add(new JedisShardInfo("127.0.0.1", 1200));
		shards.add(new JedisShardInfo("127.0.0.1", 1201));
		shards.add(new JedisShardInfo("127.0.0.1", 1202));
		shards.add(new JedisShardInfo("127.0.0.1", 1203));
		
		
		pool = new ShardedJedisPool(new GenericObjectPoolConfig(), shards, Hashing.MURMUR_HASH);
	}

	@Test
	public void redis() throws Exception {
		
		long start = currentTimeMillis();
		
		ShardedJedis jedis = pool.getResource();
		
		for (int i = 0; i < 10000; i++) {
			jedis.setex("key-" + i, 60, "algum valor: " + i);
		}
		
		
		System.out.println("Time: " + (currentTimeMillis() - start) + "ms.");
		
		
	}
	
}
