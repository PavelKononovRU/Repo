package com.exchangeinformant.controllers;

import com.exchangeinformant.model.Stock;
import com.exchangeinformant.services.BcsStockService;
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
@RequestMapping(value = "/bcs",produces = MediaType.APPLICATION_JSON_VALUE)
public class BcsRestController {

    private final BcsStockService bcsStockService;

    public BcsRestController(BcsStockService bcsStockService) {
        this.bcsStockService = bcsStockService;
    }

    @GetMapping("/get/{stock}")
    public ResponseEntity<Stock> get(@PathVariable(name = "stock") String stock) {
        return new ResponseEntity<>(bcsStockService.getStock(stock), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Stock>> getAll() {
        return new ResponseEntity<>(bcsStockService.getAllStocks(), HttpStatus.OK);
    }

    //TODO - только
    @GetMapping("/update")
    public String update() throws IOException {
        bcsStockService.updateAllStocks();
        return "OK";
    }

}
