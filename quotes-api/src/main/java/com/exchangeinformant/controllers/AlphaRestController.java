package com.exchangeinformant.controllers;

import com.exchangeinformant.model.Company;
import com.exchangeinformant.services.AlphaStockService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 22.12.2022
 * Time: 10:21
 */
@RestController
@RequestMapping("/api")
public class AlphaRestController {
    private final AlphaStockService alphaStockService;

    public AlphaRestController(AlphaStockService alphaStockService) {
        this.alphaStockService = alphaStockService;
    }

    @GetMapping("/update")
    public ResponseEntity<String> updateStockPrices() {
        alphaStockService.updateAllStocks();
        return ResponseEntity.ok("All stocks have been updated");
    }

    @GetMapping("/getStocks")
    public List<Company> getStocks() {
        return alphaStockService.getAllStocks();
    }

    @GetMapping("/{code}")
    public Company getStock(@PathVariable("code") String code) {
        return alphaStockService.getStockByCode(code);
    }

}
