package com.exchangeinformant.controllers;

import com.exchangeinformant.model.Stock;
import com.exchangeinformant.services.StockDbService;
import com.exchangeinformant.services.StockService;
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
                                                 @RequestParam(name = "dateTo", required = false, defaultValue = "#{T(java.time.LocalDateTime).now()}") LocalDateTime dateTo) {
        return stockDbService.getAllStocksByDate(dateFrom, dateTo);
    }

    @GetMapping("/directStock")
    public Stock getStockDirectlyByBcs(@RequestParam("name") String secureCode) {
        return stockService.getStockDirectly(secureCode);
    }
    @PostMapping("/directStock")
    public List<Stock> getStocksDirectlyByBcs(@RequestBody List<String> secureCodes) {
        return stockService.getStocksDirectly(secureCodes);
    }
}
