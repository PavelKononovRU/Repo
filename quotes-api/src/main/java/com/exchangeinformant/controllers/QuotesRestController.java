package com.exchangeinformant.controllers;

import com.exchangeinformant.dto.StockDto;
import com.exchangeinformant.services.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 22.12.2022
 * Time: 10:21
 */
@RestController
@RequestMapping("/api")
public class QuotesRestController {
    private final QuoteService quoteService;

    public QuotesRestController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/{stock}")
    public ResponseEntity<StockDto> getUser(@PathVariable("stock") String stock) throws IOException, URISyntaxException, InterruptedException {
        return new ResponseEntity<>(quoteService.getCurrentStock(stock), HttpStatus.OK);
    }

}
