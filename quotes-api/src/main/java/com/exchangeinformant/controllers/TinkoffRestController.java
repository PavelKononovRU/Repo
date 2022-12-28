package com.exchangeinformant.controllers;

import com.exchangeinformant.dto.TinkoffStockDTO;
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

    @GetMapping("/tinkoff/{ticker}")
    public TinkoffStock getStock(@PathVariable String ticker) {
        return tinkoffStockService.getStockByTicker(ticker);
    }

    @PostMapping("tinkoff/getStocks")
    public TinkoffStockDTO getStocks(@RequestBody List<String> tickers) {
       return tinkoffStockService.getStocksByTickers(tickers);
    }
}
