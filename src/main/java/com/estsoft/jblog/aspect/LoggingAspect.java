package com.estsoft.jblog.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	private static final Log log = LogFactory.getLog( LoggingAspect.class );
	
	 @Before("execution(* *..controller.*.*(..))")
	    public void logBefore(JoinPoint.StaticPart jpsp) {	 
		 log.debug("[jblog Log] RequestMapping Controller.. ClassName = " + jpsp.getSignature().getDeclaringType().getName() + ", MethodName = " + jpsp.getSignature().getName());		 
	    }
}
