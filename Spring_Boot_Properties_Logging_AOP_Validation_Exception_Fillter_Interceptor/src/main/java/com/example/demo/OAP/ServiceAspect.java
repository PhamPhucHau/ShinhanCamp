package com.example.demo.OAP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class ServiceAspect {
    private Logger logger= LoggerFactory.getLogger(ServiceAspect.class);
    @Before("execution(* com.example.demo.service.*.*(..))")
    public void before(JoinPoint joinPoint)
    {
        logger.info(" before called"+ joinPoint.toString());
    }
    @After("execution(* com.example.demo.service.*.*(..))")
    public void after(JoinPoint joinPoint)
    {
        logger.info("after called"+ joinPoint.toString());
    }
    @AfterThrowing("execution(* com.example.demo.service.*.*(..))")
    public void error(JoinPoint joinPoint)
    {
        logger.error("Error called"+ joinPoint.toString());
    }
}
