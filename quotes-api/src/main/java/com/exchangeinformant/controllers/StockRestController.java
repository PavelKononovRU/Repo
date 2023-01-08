package com.exchangeinformant.controllers;

import com.exchangeinformant.services.StockService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 22.12.2022
 * Time: 10:21
 */
@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class StockRestController {

    private final StockService stockService;

    public StockRestController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/")
    public String get() {
        System.err.println(stockService.getStock("AAPL").getInfoList().get(0));
        return stockService.getStock("AAPL").getInfoList().toString();
    }
    //ONLY FOR TESTING
    @GetMapping("/update")
    public String update() throws IOException {
        stockService.updateAllStocks();
        return "OK";
    }
}
