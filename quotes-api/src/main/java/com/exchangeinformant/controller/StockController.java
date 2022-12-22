package com.exchangeinformant.controller;

import com.exchangeinformant.services.QuoteService;
import lombok.AllArgsConstructor;
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
    public String getStock(@PathVariable String stockName) throws IOException, URISyntaxException, InterruptedException {
        return quoteService.getCurrentStock(stockName);
    }
}
