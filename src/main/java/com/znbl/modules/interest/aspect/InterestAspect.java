package com.znbl.modules.interest.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InterestAspect {
	
	@Before("execution(public * com.znbl.modules.interest.dao.ComicDao.*(..))")
	public void beforMethod(JoinPoint joinPoint){
		
		String methodName = joinPoint.getSignature().getName();
		
		List<Object> list = Arrays.asList(joinPoint.getArgs());
		
		System.out.println("haha"+methodName+list.toString().intern());
		
	}

}
