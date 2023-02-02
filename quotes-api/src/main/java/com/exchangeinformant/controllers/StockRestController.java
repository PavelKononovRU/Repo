package com.exchangeinformant.controllers;

import com.exchangeinformant.exception.ErrorCodes;
import com.exchangeinformant.exception.QuotesException;
import com.exchangeinformant.services.StockDbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by e-davidenko
 * Date: 12.01.2023
 * Time: 15:10
 */

@RestController
public class StockRestController {

    private final StockDbService stockDbService;
    private final StockService stockService;

    public StockRestController(StockDbService stockDbService, StockService stockService) {
        this.stockDbService = stockDbService;
        this.stockService = stockService;
    }

    @GetMapping("/stock")
    public Stock getStock(@RequestParam("name") String secureCode) {
        return stockDbService.getStock(secureCode);
    }

    @GetMapping("/stock/query")
    public Stock getStockWithParameters(@RequestParam(name = "dateFrom", required = false, defaultValue = "#{T(java.time.LocalDateTime).now().toLocalDate().atStartOfDay()}") LocalDateTime dateFrom,
                                                    @RequestParam(name = "dateTo", required = false, defaultValue = "#{T(java.time.LocalDateTime).now()}") LocalDateTime dateTo,
                                                    @RequestParam("stock") String secureCode) {
        return stockDbService.getStockByDate(secureCode, dateFrom, dateTo);
    }

    @GetMapping("/all")
    public List<Stock> getAllStocksByDates(@RequestParam(name = "dateFrom", required = false, defaultValue = "#{T(java.time.LocalDateTime).now().toLocalDate().atStartOfDay()}") LocalDateTime dateFrom,
    @GetMapping("/allWithDates")
    public ResponseEntity<?> getAllStocksByDates(@RequestParam(name = "dateFrom", required = false, defaultValue = "#{T(java.time.LocalDateTime).now().toLocalDate().atStartOfDay()}") LocalDateTime dateFrom,
                                                 @RequestParam(name = "dateTo", required = false, defaultValue = "#{T(java.time.LocalDateTime).now()}") LocalDateTime dateTo) {
        return new ResponseEntity<>(stockDbService.getAllStocksByDate(dateFrom, dateTo), HttpStatus.OK);
    }

    @GetMapping("/directStock")
    public Stock getStockDirectlyByBcs(@RequestParam("name") String secureCode) {
        return stockService.getStockDirectly(secureCode);
    @GetMapping("/all")
    public ResponseEntity<?> getAllStocks() {
        return new ResponseEntity<>(stockDbService.getAllStocks(), HttpStatus.OK);
    }
    @PostMapping("/availableStocks")
    public ResponseEntity<?> getAllAvailableStocks(@RequestBody List<String> securityCodes) {
        return new ResponseEntity<>(stockDbService.getAllAvailableStocksByCodes(securityCodes), HttpStatus.OK);
    }

    // для наглядности
    @GetMapping("/error")
    public ResponseEntity<?> getError() {
        throw new QuotesException(ErrorCodes.UPDATE_PROBLEM.name());
    }
    @PostMapping("/directStock")
    public List<Stock> getStocksDirectlyByBcs(@RequestBody List<String> secureCodes) {
        return stockService.getStocksDirectly(secureCodes);
    }
}
