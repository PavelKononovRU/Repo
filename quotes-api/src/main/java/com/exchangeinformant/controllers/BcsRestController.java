//package com.exchangeinformant.controllers;
//
//import com.exchangeinformant.model.Stock;
//import com.exchangeinformant.services.StockServiceSwitcher;
//import com.exchangeinformant.util.Bcs;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * Created in IntelliJ
// * User: e-davidenko
// * Date: 22.12.2022
// * Time: 10:21
// */
//@RestController
//@Bcs
//public class BcsRestController {
//
//    private final StockServiceSwitcher stockServiceSwitcher;
//
//    public BcsRestController(StockServiceSwitcher stockServiceSwitcher) {
//        this.stockServiceSwitcher = stockServiceSwitcher;
//    }
//
//    @GetMapping("/inform")
//    public String getInfo() {
//        return "its bcs";
//    }
//
//
//
//    @GetMapping("/get/{stock}")
//    public ResponseEntity<Stock> get(@PathVariable(name = "stock") String stock) {
//        return new ResponseEntity<>(stockServiceSwitcher.getCurrentService().getStock(stock), HttpStatus.OK);
//    }
//
//    @GetMapping("/get/all")
//    public ResponseEntity<List<Stock>> getAll() {
//        return new ResponseEntity<>(stockServiceSwitcher.getCurrentService().getAllStocks(), HttpStatus.OK);
//    }
//
//    //TODO - только премиум
//    @GetMapping("/update")
//    public String update() {
//        stockServiceSwitcher.getCurrentService().updateAllStocks();
//        return "OK";
//    }
//
//}
