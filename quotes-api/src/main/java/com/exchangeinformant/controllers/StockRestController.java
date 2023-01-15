package com.exchangeinformant.controllers;

import com.exchangeinformant.exception.ErrorCodes;
import com.exchangeinformant.exception.QuotesUpdateException;
import com.exchangeinformant.services.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by e-davidenko
 * Date: 12.01.2023
 * Time: 15:10
 */

@RestController
public class StockRestController {


    private final StockService stockService;

    public StockRestController(StockService stockService) {
        this.stockService = stockService;
    }

    // Только для тестирования
    @GetMapping("/info")
    public ResponseEntity<?> getInfo() {
        return new ResponseEntity<>(stockService.getClass().getName(), HttpStatus.OK);
    }


    @GetMapping("/error")
    public ResponseEntity<?> getError() {
        throw new QuotesUpdateException(ErrorCodes.UPDATE_PROBLEM.name());
    }

    //TODO
}
