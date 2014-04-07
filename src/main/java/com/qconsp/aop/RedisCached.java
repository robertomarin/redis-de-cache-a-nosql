package com.qconsp.aop;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;


@Retention(RUNTIME)
public @interface RedisCached {
	
	int secondsToCache() default 60;
	
	

}
