package com.exchangeinformant.controller;

import com.exchangeinformant.dto.StockDto;
import com.exchangeinformant.services.QuoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@AllArgsConstructor
public class StockController {
    private final QuoteService quoteService;

    @GetMapping("/stocks/{stockName}")
    public ResponseEntity<StockDto> getStock(@PathVariable String stockName) throws IOException, URISyntaxException, InterruptedException {
        return new ResponseEntity<>(quoteService.getCurrentStock(stockName), HttpStatus.OK);
    }
}
