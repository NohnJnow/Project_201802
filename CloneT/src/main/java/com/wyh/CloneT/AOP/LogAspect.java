package com.wyh.CloneT.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author Wu YuHao thecoco
 * @date 2018/9/23 下午2:53
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @Before("execution(* com.wyh.CloneT.Controller.IndexController.*(..))")
    public void beforeMethod(JoinPoint joinPoint){
        logger.info("before method");

    }
    @After("execution(* com.wyh.CloneT.Controller.IndexController.*(..))")
    public void afterMethod(JoinPoint joinPoint){
        logger.info("After method");
    }
}
