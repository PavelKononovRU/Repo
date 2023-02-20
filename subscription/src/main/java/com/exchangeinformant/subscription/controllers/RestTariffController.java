package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.service.TariffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Контроллер тарифов", description = "CRUD операции с тарифами на подписки.")
public class RestTariffController {

    private final TariffService tariffService;

    @Autowired
    public RestTariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping("/tariff/{id}")
    @Operation(summary = "Получить тариф по идентификатору.")
    public ResponseEntity<Tariff> getTariff(@PathVariable Long id) {
        return ResponseEntity.ok(tariffService.getTariff(id));
    }

    @GetMapping("/tariff")
    @Operation(summary = "Получить все тарифы.")
    public ResponseEntity<List<Tariff>> getTariffs() {
        return ResponseEntity.ok(tariffService.getAllTariff());
    }

    @PostMapping("/tariff")
    @Operation(summary = "Создать тариф.")
    public ResponseEntity<HttpStatus> createTariff(@RequestBody Tariff tariff) {
        tariffService.createTariff(tariff);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/tariff")
    @Operation(summary = "Обновить тариф.")
    public ResponseEntity<HttpStatus> updateTariff(@RequestBody Tariff tariff) {
        tariffService.updateTariff(tariff);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/tariff/{id}")
    @Operation(summary = "Удалить тариф.")
    public ResponseEntity<HttpStatus> deleteTariff(@PathVariable Long id) {
        tariffService.deleteTariff(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

