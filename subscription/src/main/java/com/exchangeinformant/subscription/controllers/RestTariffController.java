package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.model.Tariff;
import com.exchangeinformant.subscription.service.TariffService;
<<<<<<< subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestTariffController.java
import jakarta.annotation.security.RolesAllowed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
=======
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
>>>>>>> subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestTariffController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
<<<<<<< subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestTariffController.java
@RolesAllowed({"ADMIN"})
@Tag(name = "Контроллер тарифов", description = "CRUD операции с тарифами на подписки.")
=======
@Tag(name = "Контроллер тарифов", description = "CRUD операции с тарифами на подписки.")
>>>>>>> subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestTariffController.java
public class RestTariffController {

    private final TariffService tariffService;

    @Autowired
    public RestTariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping("/tariff/{id}")
<<<<<<< subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestTariffController.java
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Получить тариф по идентификатору.")
=======
    @Operation(summary = "Получить тариф по идентификатору.")
>>>>>>> subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestTariffController.java
    public ResponseEntity<Tariff> getTariff(@PathVariable Long id) {
        return ResponseEntity.ok(tariffService.getTariff(id));
    }

    @GetMapping("/tariff")
<<<<<<< subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestTariffController.java
    @RolesAllowed({"USER", "ADMIN"})
    @Operation(summary = "Получить все тарифы.")
=======
    @Operation(summary = "Получить все тарифы.")
>>>>>>> subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestTariffController.java
    public ResponseEntity<List<Tariff>> getTariffs() {
        return ResponseEntity.ok(tariffService.getAllTariff());
    }

    @PostMapping("/tariff")
<<<<<<< subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestTariffController.java
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Создать тариф.")
=======
    @Operation(summary = "Создать тариф.")
>>>>>>> subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestTariffController.java
    public ResponseEntity<HttpStatus> createTariff(@RequestBody Tariff tariff) {
        tariffService.createTariff(tariff);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/tariff")
<<<<<<< subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestTariffController.java
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Обновить тариф.")
=======
    @Operation(summary = "Обновить тариф.")
>>>>>>> subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestTariffController.java
    public ResponseEntity<HttpStatus> updateTariff(@RequestBody Tariff tariff) {
        tariffService.updateTariff(tariff);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/tariff/{id}")
<<<<<<< subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestTariffController.java
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Удалить тариф.")
=======
    @Operation(summary = "Удалить тариф.")
>>>>>>> subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestTariffController.java
    public ResponseEntity<HttpStatus> deleteTariff(@PathVariable Long id) {
        tariffService.deleteTariff(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

