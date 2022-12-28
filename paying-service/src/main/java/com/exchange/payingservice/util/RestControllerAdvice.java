package com.exchange.payingservice.util;

import com.exchange.payingservice.dto.CardDTO;
import com.exchange.payingservice.dto.StatusCards;
import com.exchange.payingservice.entity.Card;
import com.exchange.payingservice.mappers.CardMapper;
import com.exchange.payingservice.repository.CardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestControllerAdvice {

    public static ResponseEntity<Object> generateResponsePost(Card card) {
        System.out.println(card.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", new StatusCards("Карта сохранена",
                "Управляйте картами в платежной информации"));
        map.put("create_at", LocalDateTime.now());
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", message);
        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> generateResponse(CardDTO cardDTO) {
        if (cardDTO == null) {
            throw new UsernameNotFoundException("Card not found");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", cardDTO);
        map.put("create_at", LocalDateTime.now());
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

}
