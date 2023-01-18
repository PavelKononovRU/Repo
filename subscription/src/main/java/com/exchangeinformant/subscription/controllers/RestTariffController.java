package com.exchangeinformant.subscription.controllers;
import com.exchangeinformant.subscription.dto.TariffDTO;

import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestTariffController {

    private final TariffService tariffService;

    @Autowired
    public RestTariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping("/tariff/{id}")
    public ResponseEntity<Tariff> getTariff(@PathVariable Long id) {
        return ResponseEntity.ok(tariffService.getTariff(id));
    }

    @GetMapping("/tariff")
    public ResponseEntity<List<Tariff>> getTariffs() {
        return ResponseEntity.ok(tariffService.getAllTariff());
    }

    @PostMapping("/tariff")
    public ResponseEntity<HttpStatus> createTariff(@RequestBody Tariff tariff) {
        tariffService.createTariff(tariff);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/tariff")
    public ResponseEntity<HttpStatus> updateTariff(@RequestBody Tariff tariff) {
        tariffService.updateTariff(tariff);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/tariff/{id}")
    public ResponseEntity<HttpStatus> deleteTariff(@PathVariable Long id) {
        tariffService.deleteTariff(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

