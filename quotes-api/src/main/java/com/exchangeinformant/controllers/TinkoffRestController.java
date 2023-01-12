//package com.exchangeinformant.controllers;
//
//import com.exchangeinformant.model.Stock;
//import com.exchangeinformant.services.StockService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("tinkoff/")
//public class TinkoffRestController {
//    private final StockService stockService;
//
//    @GetMapping("/update")
//    public ResponseEntity<String> updateStockPrices() {
//        stockService.updateAllStocks();
//        return ResponseEntity.ok("All stocks have been updated");
//    }
//
//    @GetMapping("/getStocks")
//    public List<Stock> getStocks() {
//       return stockService.getAllStocks();
//    }
//
//    @GetMapping("/{code}")
//    public Stock getStock(@PathVariable("code") String code) {
//        return stockService.getStock(code);
//    }
//
//    @PostMapping("/getSomeStocks")
//    public List<Stock> getSomeStocks(@RequestBody List<String> codes) {
//        return stockService.getStocksByCodes(codes);
//    }
//}
