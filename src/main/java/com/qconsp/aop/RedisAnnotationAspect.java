package com.qconsp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//@Aspect
//@EnableAspectJAutoProxy
//@Component
public class RedisAnnotationAspect {

	private Jedis jedis;

	private Gson gson;
	
	public RedisAnnotationAspect() {
		jedis = new Jedis("127.0.0.1", 1200);
		gson = new GsonBuilder().create();
	}

	@Around("execution(* com.qconsp..*.*(..)) && @annotation(redisCached)")
	private Object around(ProceedingJoinPoint pjp, final RedisCached redisCached) throws Throwable {

		String json = jedis.get(pjp.getArgs()[0].toString());

		if (json != null) {
			Class type = ((MethodSignature) pjp.getSignature()).getReturnType();
			Object object = gson.fromJson(json, type);

			if (object != null) {
				System.out.println("@@@ Recuperado pelo REDIS!");
				return object;
			}
		}

		Object proceed = pjp.proceed();

		System.out.println("@@@ Recuperado pelo BANCO!: ");

		jedis.setex(pjp.getArgs()[0].toString(), redisCached.secondsToCache(), gson.toJson(proceed));

		return proceed;
	}

}
