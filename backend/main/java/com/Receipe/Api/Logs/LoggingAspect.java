package com.Receipe.Api.Logs;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut for all methods in the controller package
    @Around("execution(* com.Receipe.Api.Controller..*(..))")
    public Object logControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();

        // Log the start time
        Instant start = Instant.now();
        logger.info("Started executing method: {} at {}", methodName, start);

        try {
            // Proceed with the actual method execution
            Object response = joinPoint.proceed();

            // Log service call and response
            logger.info("Successfully executed method: {}", methodName);

            return response;
        } catch (Exception ex) {
            // Log exception and rethrow it
            logger.error("Exception occurred in method: {}", methodName, ex);
            throw ex;
        } finally {
            // Log the response time
            Instant end = Instant.now();
            logger.info("Completed executing method: {} at {}", methodName, end);
            logger.info("Execution time for method {}: {} ms", methodName, Duration.between(start, end).toMillis());
        }
    }
}

