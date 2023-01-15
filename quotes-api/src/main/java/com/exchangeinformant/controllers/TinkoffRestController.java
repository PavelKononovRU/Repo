//package com.exchangeinformant.controllers;
//
//import com.exchangeinformant.model.Stock;
//import com.exchangeinformant.services.StockServiceSwitcher;
//import com.exchangeinformant.util.Tinkoff;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RefreshScope
//@Tinkoff
//public class TinkoffRestController {
//
//    private final StockServiceSwitcher stockServiceSwitcher;
//
//
//    public TinkoffRestController(StockServiceSwitcher stockServiceSwitcher) {
//        this.stockServiceSwitcher = stockServiceSwitcher;
//    }
//
//    @GetMapping("/inform")
//    public String getInfo() {
//        return "its tinkoff";
//    }
//
//
//
//    @GetMapping("/update")
//    public ResponseEntity<String> updateStockPrices() {
//        stockServiceSwitcher.getCurrentService().updateAllStocks();
//        return ResponseEntity.ok("All stocks have been updated");
//    }
//
//    @GetMapping("/getStocks")
//    public List<Stock> getStocks() {
//       return stockServiceSwitcher.getCurrentService().getAllStocks();
//    }
//
//    @GetMapping("/{code}")
//    public Stock getStock(@PathVariable("code") String code) {
//        return stockServiceSwitcher.getCurrentService().getStock(code);
//    }
//
//    @PostMapping("/getSomeStocks")
//    public List<Stock> getSomeStocks(@RequestBody List<String> codes) {
//        return stockServiceSwitcher.getCurrentService().getStocksByCodes(codes);
//    }
//}
