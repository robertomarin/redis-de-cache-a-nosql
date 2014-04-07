package com.qconsp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


@Aspect
@EnableAspectJAutoProxy
@Component
public class AspectjBean {
	
	@Around("execution(* *Aspectado(..))")
	private Object around(final ProceedingJoinPoint pjp) throws Throwable {

		Object proceed = pjp.proceed();
		
		System.out.println("Dentro do aspecto: " + proceed);
		
		return proceed;
	}

}
