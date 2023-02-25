package com.exchange.payingservice.util.Annotations.Monitor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Aspect
@Slf4j
public class MonitorAspect {

    /*PointCuts*/
    @Pointcut("within(@com.exchange.payingservice.util.Annotations.Monitor.Monitor *)")
    public void beanAnnotatedRestInfo() {
    }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Pointcut("publicMethod() && beanAnnotatedRestInfo()")
    public void publicMethodInsideAClassMarkedWithAtMonitor() {
    }

    /*Advices*/
    @Before("publicMethodInsideAClassMarkedWithAtMonitor())")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        log.info("------------------------------------------------");
        log.info("**REQUEST**");
        log.info("METHOD: " + "*" + joinPoint.getSignature().getName() + "*");

        if (args.length == 0) {
            log.info("NO ARGUMENTS");
        } else {
            log.info("ARGUMENTS: <<<" + Arrays.stream(args).map(String::valueOf)
                    .collect(Collectors.joining(", ")) + ">>>");
        }
        log.info("------------------------------------------------");
    }

    @AfterReturning(pointcut = "publicMethodInsideAClassMarkedWithAtMonitor()", returning = "retVal")
    public void logAfter(JoinPoint jp, Object retVal) {
        log.info("**RESPONSE**");
        log.info("METHOD: " + "*" + jp.getSignature().getName() + "*");

        if (retVal.equals("")) {
            log.info("NO ARGUMENTS");
        } else {
            log.info("ARGUMENT: <<<" + retVal + ">>>");
        }

        log.info("------------------------------------------------");
    }

    @Around("publicMethodInsideAClassMarkedWithAtMonitor()")
    public Object logTime(ProceedingJoinPoint proceedingJoinPoint) {
        String name = proceedingJoinPoint.getSignature().getName();

        long startTime = System.nanoTime();

        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException("Произошла ошибка" + e);
        } finally {
            long finishTime = System.nanoTime();

            String totatTime = String.format("%.3f", (double) (finishTime - startTime) / 1_000_000_000);
            log.info("Время выполнения метода " + name.toUpperCase() + ": "
                    + totatTime + " s");

        }
    }
}

