package com.exchangeinformant.controllers;

import com.exchangeinformant.dto.StockDTO;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.model.Company;
import com.exchangeinformant.services.CompanyService;
import com.exchangeinformant.services.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 22.12.2022
 * Time: 10:21
 */
@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class StockRestController {
    private final StockService stockService;

    public StockRestController(StockService stockService, CompanyService companyService) {
        this.stockService = stockService;
    }

    @GetMapping(value = "/{stock}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> getStockPrice(@PathVariable("stock") String stock) throws IOException, URISyntaxException, InterruptedException {
        Stock stockObject = stockService.getStockPrice(stock);
        return new ResponseEntity<>(new StockDTO(stockObject.getSymbol(),stockObject.getPrice()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStockPrices() {
        return new ResponseEntity<>(stockService.getAllStocks(),HttpStatus.OK);
    }
}
