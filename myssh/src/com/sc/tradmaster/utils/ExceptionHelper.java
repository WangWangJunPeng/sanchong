package com.sc.tradmaster.utils;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.sc.tradmaster.interceptor.LogHandlerInterceptor;

/**
 * 异常信息记录
 * @author Administrator
 *
 */
@Component
@Aspect
public class ExceptionHelper {
	
	//private static Log log = LogFactory.getLog(LogHandlerInterceptor.class);
	private static Logger log = Logger.getLogger(ExceptionHelper.class);

	// 异常信息记录处理
	@Pointcut("within(com.sc.tradmaster.service..*)")
	public void exceptionPointCut() {
	}

	@AfterThrowing(throwing="ex",pointcut="exceptionPointCut()")
	public void throwingMethod( Throwable ex) {
		StackTraceElement[] sta = ex.getStackTrace();
		StackTraceElement s = sta[0];
		int num = s.getLineNumber();
		log.error(DateTime.toString1(new Date()));
		log.error(ex);
		log.error(s);
	}
}
