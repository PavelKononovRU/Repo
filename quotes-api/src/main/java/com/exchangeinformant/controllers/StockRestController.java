package com.exchangeinformant.controllers;

import com.exchangeinformant.model.Stock;
import com.exchangeinformant.services.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

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

    @GetMapping("/get/{stock}")
    public ResponseEntity<Stock> get(@PathVariable(name = "stock") String stock) {
        return new ResponseEntity<>(stockService.getStock(stock), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Stock>> getAll() {
        return new ResponseEntity<>(stockService.getAllStocks(), HttpStatus.OK);
    }

    //TODO - только
    @GetMapping("/update")
    public String update() throws IOException {
        stockService.updateAllStocks();
        return "OK";
    }

}
