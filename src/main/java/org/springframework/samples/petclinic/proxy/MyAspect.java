package org.springframework.samples.petclinic.proxy;

//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;

import org.slf4j.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class MyAspect {

	Logger logger = LoggerFactory.getLogger(MyAspect.class);

	@Around("@annotation(MyAOP)") // @annotation(MyAOP) 라는 어노테이션을 쓴거 안에서 함수가 구동한다는것
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable { // joinPoint에는
																						// @annotation(MyAOP)
																						// 어노테이션이
																						// 붙은
																						// 메소드들이
																						// 넘어옴
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object proceed = joinPoint.proceed(); // 넘어온 joinPoint를 구동 시키고
		stopWatch.stop();
		logger.debug(stopWatch.prettyPrint()); // Logger의 기능을 사용하되 Aspect만의 기능도 추가하여 구동
		return proceed;
	}

}
