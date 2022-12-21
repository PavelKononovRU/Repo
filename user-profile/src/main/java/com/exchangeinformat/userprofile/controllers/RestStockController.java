package com.exchangeinformat.userprofile.controllers;

import com.exchangeinformat.userprofile.model.Stock;
import com.exchangeinformat.userprofile.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/stock")
public class RestStockController {
    private final StockService stockService;

    @Autowired
    public RestStockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/findOne")
    public ResponseEntity<Stock> getStock(Long id) {
        return ResponseEntity.ok(stockService.getStock(id));
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getStocks() {
        return ResponseEntity.ok(stockService.getAllStock());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createStock(@RequestBody Stock stock) {
        stockService.createStock(stock);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateStock(@RequestBody Stock stock) {
        stockService.updateStock(stock);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteStock(Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
