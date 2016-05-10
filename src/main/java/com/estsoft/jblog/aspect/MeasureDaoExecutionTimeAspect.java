package com.estsoft.jblog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class MeasureDaoExecutionTimeAspect {
	
	@Around("execution( * *..dao.*.*(..) )" )
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		String taskName = pjp.getTarget().getClass()+"."+pjp.getSignature().getName();
							//클래스 이름				.		메소드 이름
		//before
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		System.out.println( "call [around.before advice]" );
		
		
		Object result = pjp.proceed();
		
		//After
		stopwatch.stop();
		long time = stopwatch.getLastTaskTimeMillis();
		System.out.println("time: ["+ taskName +"] "+time+"ms");
		
		return result;
		
	}
}
	
