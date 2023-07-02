/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 7월 2일 JerryDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-07-02
작성시간 : 오후 6:30
작성용도 : Class files related to Spring MVC AOP (Aspect Oriented Programming) configuration
*/

package com.example.jerry.commons.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAdvice {

    private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

    @Around("execution(* com.example.jerry..controller..*Controller.*(..))"
            + " execution(* com.example.jerry..service..*Impl.*(..))"
            + " execution(* com.example.jerry..persistence..*Impl.*(..))")
    public Object logPrint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String name = "";

        if (type.contains("Controller")) {
            name = "Controller : ";
        } else if (type.contains("Service")) {
            name = "Service : ";
        } else if (type.contains("DAO")) {
            name = "Persistence : ";
        }

        long end = System.currentTimeMillis();

        logger.info(name + type + "." + proceedingJoinPoint.getSignature().getName() + "()");
        logger.info("Argument/Parameter : " + Arrays.toString(proceedingJoinPoint.getArgs()));
        if (result != null) {
            logger.info("Return Value : " + result.toString());
        } else {
            logger.info("Return Type : void");
        }
        logger.info("Running Time : " + (end - start));
        logger.info("----------------------------------------------------------------");

        return result;
    }

}