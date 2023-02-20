package com.exchange.payingservice.util.Annotations.ExecuteTimeAnnotation;

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
public class ExecuteTimeAspect {

    @Pointcut("@annotation(com.exchange.payingservice.util.Annotations.ExecuteTimeAnnotation.ExecuteTime)")
    public void executeTimeMethod(){}

    @Around("executeTimeMethod()")
    public Object ExecuteTime(ProceedingJoinPoint jp) {
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        String methodName = methodSignature.getName();
        long start = System.nanoTime();

        try {
            return jp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            long finishTime=System.nanoTime();
            log.info("Время, затраченное на работу метода " + methodName +
                    " составило " + (finishTime-start)/1000 + " мс");
        }
    }
}
