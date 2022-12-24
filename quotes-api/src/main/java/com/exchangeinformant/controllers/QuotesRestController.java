package com.exchangeinformant.controllers;

import com.exchangeinformant.dto.StockDTO;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.model.StockInfo;
import com.exchangeinformant.services.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class QuotesRestController {
    private final QuoteService quoteService;

    public QuotesRestController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping(value = "/{stock}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> getUser(@PathVariable("stock") String stock) throws IOException, URISyntaxException, InterruptedException {
        Stock stockObject = quoteService.getStockPrice(stock);
        return new ResponseEntity<>(new StockDTO(stockObject.getSymbol(),stockObject.getPrice()), HttpStatus.OK);
    }

    @GetMapping(value = "info/{stock}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockInfo> getStockInfo(@PathVariable("stock") String stock) throws IOException, URISyntaxException, InterruptedException {
        return new ResponseEntity<>(quoteService.getStockInfo(stock) , HttpStatus.OK);
    }

}
