package com.exchangeinformant.controllers;

import com.exchangeinformant.model.Info;
import com.exchangeinformant.services.AlphaStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<Info> getStocks() {
        return alphaStockService.getAllStocks();
    }

    @GetMapping("/{code}")
    public Info getStock(@PathVariable("code") String code) {
        return alphaStockService.getStockByCode(code);
    }

    @PostMapping("/getSomeStocks")
    public List<Info> getSomeStocks(@RequestBody List<String> codes) {
        return alphaStockService.getStocksByCodes(codes);
    }

}
