package com.exchangeinformant.controllers;

import com.exchangeinformant.services.BcsStockService;
import com.exchangeinformant.services.StockDbService;
import com.exchangeinformant.services.TinkoffStockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by e-davidenko
 * Date: 12.01.2023
 * Time: 15:10
 */

@RestController
public class StockRestController {

    private final StockDbService stockDbService;
    private final BcsStockService bcsStockService;

    public StockRestController(StockDbService stockDbService, BcsStockService bcsStockService) {
        this.stockDbService = stockDbService;
        this.bcsStockService = bcsStockService;
    }

    @GetMapping("/stock")
    public ResponseEntity<?> getStock(@RequestParam("name") String secureCode) {
        return new ResponseEntity<>(stockDbService.getStock(secureCode), HttpStatus.OK);
    }

    @GetMapping("/stock/query")
    public ResponseEntity<?> getStockWithParameters(@RequestParam(name = "dateFrom", required = false, defaultValue = "#{T(java.time.LocalDateTime).now().toLocalDate().atStartOfDay()}") LocalDateTime dateFrom,
                                                    @RequestParam(name = "dateTo", required = false, defaultValue = "#{T(java.time.LocalDateTime).now()}") LocalDateTime dateTo,
                                                    @RequestParam("stock") String secureCode) {
        return new ResponseEntity<>(stockDbService.getStockByDate(secureCode, dateFrom, dateTo), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStocksByDates(@RequestParam(name = "dateFrom", required = false, defaultValue = "#{T(java.time.LocalDateTime).now().toLocalDate().atStartOfDay()}") LocalDateTime dateFrom,
                                                 @RequestParam(name = "dateTo", required = false, defaultValue = "#{T(java.time.LocalDateTime).now()}") LocalDateTime dateTo) {
        return new ResponseEntity<>(stockDbService.getAllStocksByDate(dateFrom, dateTo), HttpStatus.OK);
    }

    @GetMapping("/bcsStock")
    public ResponseEntity<?> getStockDirectlyByBcs(@RequestParam("name") String secureCode) {
        return new ResponseEntity<>(bcsStockService.getStockDirectly(secureCode), HttpStatus.OK);
    }
    @PostMapping("/bcsStock")
    public ResponseEntity<?> getStocksDirectlyByBcs(@RequestBody List<String> secureCodes) {
        return new ResponseEntity<>(bcsStockService.getStocksDirectly(secureCodes), HttpStatus.OK);
    }
}
