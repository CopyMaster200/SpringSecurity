package com.spring.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.spring.demo..*(..)) && " +
            "(@within(org.springframework.web.bind.annotation.RestController) || " +
            "@within(org.springframework.stereotype.Service))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        long start = System.currentTimeMillis();
        log.info("Entering method: {}.{} with arguments: {}", className, methodName, joinPoint.getArgs());

        Object result = joinPoint.proceed();

        long duration = System.currentTimeMillis() - start;
        log.info("Exiting method: {}.{}; Execution time: {} ms", className, methodName, duration);

        return result;
    }
}

