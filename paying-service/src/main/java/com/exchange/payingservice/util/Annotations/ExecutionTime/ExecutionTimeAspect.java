package com.exchange.payingservice.util.Annotations.ExecutionTime;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ExecutionTimeAspect {

    @Pointcut("@annotation(com.exchange.payingservice.util.Annotations.ExecutionTime.ExecutionTime)")
    public void executeTimeMethod() {
    }

    @Around("executeTimeMethod()")
    public Object ExecutionTime(ProceedingJoinPoint jp) {
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        String methodName = methodSignature.getName();
        long startTime = System.nanoTime();

        try {
            return jp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException("Error" + e);
        } finally {
            long finishTime = System.nanoTime();

            String totatTime = String.format("%.3f", (double) (finishTime - startTime) / 1_000_000_000);

            log.info("------------------------------------------------");
            log.info("Время, затраченное на работу метода " + methodName.toUpperCase() +
                    " составило: " + totatTime + " s");
            log.info("------------------------------------------------");

        }
    }
}
