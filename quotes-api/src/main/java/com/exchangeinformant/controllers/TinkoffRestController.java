package com.exchangeinformant.controllers;

import com.exchangeinformant.dto.TinkoffStockDTO;
import com.exchangeinformant.model.Company;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.model.TinkoffStock;
import com.exchangeinformant.services.TinkoffStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tinkoff/")
public class TinkoffRestController {
    private final TinkoffStockService tinkoffStockService;

    @GetMapping("/update")
    public ResponseEntity<String> updateStockPrices() {
        tinkoffStockService.updateAllStocks();
        return ResponseEntity.ok("All stocks have been updated");
    }

    @GetMapping("/getStocks")
    public List<Company> getStocks() {
       return tinkoffStockService.getAllStocks();
    }

    @GetMapping("/{code}")
    public Company getStock(@PathVariable("code") String code) {
        return tinkoffStockService.getStockByCode(code);
    }
}
