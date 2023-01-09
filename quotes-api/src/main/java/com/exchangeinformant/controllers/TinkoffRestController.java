package com.exchangeinformant.controllers;

import com.exchangeinformant.dto.TinkoffStockDTO;
import com.exchangeinformant.model.Company;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.model.TinkoffStock;
import com.exchangeinformant.services.TinkoffStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TinkoffRestController {
    private final TinkoffStockService tinkoffStockService;

    @GetMapping("/tinkoff/update")
    public void updateStockPrices() {
        tinkoffStockService.updateAllStocks();
    }

    @GetMapping("tinkoff/getStocks")
    public List<Company> getStocks() {
       return tinkoffStockService.getAllStocks();
    }
}
