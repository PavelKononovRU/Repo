package com.exchange.payingservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestControllerAdvice {
    public static ResponseEntity<Object> generateResponse(Object resObject) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", resObject);
        map.put("create_at", LocalDateTime.now());
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", message);
        return new ResponseEntity<>(map, status);
    }
}
