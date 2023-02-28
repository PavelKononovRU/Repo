package com.exchange.payingservice.util.Annotations.Monitor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Aspect
@Component
@Slf4j
public class MonitorAspect {
    @Pointcut("within(@com.exchange.payingservice.util.Annotations.Monitor.Monitor *)")
    public void beanAnnotatedRestInfo() {
    }

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {
    }

    @Pointcut("publicMethod() && beanAnnotatedRestInfo()")
    public void publicMethodInsideAClassMarkedWithAtMonitor() {
    }

    @Around("publicMethodInsideAClassMarkedWithAtMonitor()")
    public Object Scanner(ProceedingJoinPoint pjp) throws Throwable {
        String name = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        String[] nameParams = ((MethodSignature) pjp.getSignature()).getParameterNames();

        //logBefore
        logBefore(args, nameParams, name);

        //invoke method
        var start = Instant.now();
        Object retVal = pjp.proceed();
        var finish = Instant.now();
        var executionTime = (double) (Duration.between(start, finish).toMillis());

        //logAfter
        logAfter(name, retVal);

        //count time
        log.info("Время выполнения метода " + name + " составило " + executionTime + " ms");
        System.out.println();
        return retVal;
    }

    static void logBefore(Object[] args, String[] nameParams, String methodName) {
        System.out.println("=============================================================================");
        log.info("**REQUEST**");

        if (args.length == 0) {
            log.info("ПАРАМЕТРЫ ОТСУТСТВУЮТ");
        } {
            log.info("METHOD " + methodName.toUpperCase() + " STARTED");
        }
        if (Objects.nonNull(nameParams) && args.length == nameParams.length) {
            Map<String, Object> values = new HashMap<>();
            for (int i = 0; i < nameParams.length; i++) {
                values.put(nameParams[i], args[i]);
            }
            log.info("PARAMS: " + values);
            System.out.println();
        }
    }

    static void logAfter(String name, Object retVal) {
        log.info("**RESPONSE**");

        if (retVal == null) {
            log.info("МЕТОД НИЧЕГО НЕ ВОЗВРАЩАЕТ");
        } else {
            if (retVal instanceof ResponseEntity<?>) {

                String status = ((ResponseEntity<?>) retVal).getStatusCode().toString();
                String body = Objects.requireNonNull(((ResponseEntity<?>) retVal).getBody()).toString();
                Set<Map.Entry<String, List<String>>> headers = ((ResponseEntity<?>) retVal).getHeaders().entrySet();
                log.info("РЕЗУЛЬТАТ ЗАПРОСА МЕТОДА: " + name.toUpperCase() +
                                " STATUS: " + status);
                log.info("BODY: " + body);
                log.info("HEADERS: " + headers);
            }
        }
    }
}
