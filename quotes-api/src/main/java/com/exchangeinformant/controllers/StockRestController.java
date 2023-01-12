package com.exchangeinformant.controllers;

import com.exchangeinformant.services.StockService;
import com.exchangeinformant.util.ErrorCodes;
import com.exchangeinformant.util.QuotesUpdateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by e-davidenko
 * Date: 12.01.2023
 * Time: 15:10
 */

@RestController
@RequestMapping("/quotes")
public class StockRestController {




    @GetMapping("/error")
    public ResponseEntity<?> getError() {
        throw new QuotesUpdateException(ErrorCodes.BAD_GATEWAY.name());
    }
}
