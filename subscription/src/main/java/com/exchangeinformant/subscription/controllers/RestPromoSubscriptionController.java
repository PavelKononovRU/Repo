package com.exchangeinformant.subscription.controllers;

import com.exchangeinformant.subscription.dto.PromoSubscriptionDTO;
import com.exchangeinformant.subscription.repository.PromoSubscriptionRepository;
import com.exchangeinformant.subscription.service.PromoSubscriptionService;
<<<<<<< subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestPromoSubscriptionController.java
import jakarta.annotation.security.RolesAllowed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
=======
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
>>>>>>> subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestPromoSubscriptionController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
<<<<<<< subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestPromoSubscriptionController.java
@RolesAllowed({"ADMIN", "USER"})
@Tag(name = "Контроллер промоподписок", description = "CRUD операции с промоподписками.")
public class RestPromoSubscriptionController {
    private final PromoSubscriptionService promoSubscriptionService;
=======
@Tag(name = "Контроллер промоподписок", description = "CRUD операции с промоподписками.")
public class RestPromoSubscriptionController {
    private final PromoSubscriptionService promoSubscriptionService;
    private final PromoSubscriptionRepository promoSubscriptionRepository;

>>>>>>> subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestPromoSubscriptionController.java

    @Autowired
    public RestPromoSubscriptionController(PromoSubscriptionService promoSubscriptionService) {
        this.promoSubscriptionService = promoSubscriptionService;
    }

    @GetMapping("/promosubscription/{id}")
<<<<<<< subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestPromoSubscriptionController.java
    @RolesAllowed({"USER", "ADMIN"})
    @Operation(summary = "Получить промоподписку по идентификатору.")
=======
    @Operation(summary = "Получить промоподписку по идентификатору.")
>>>>>>> subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestPromoSubscriptionController.java
    public ResponseEntity<PromoSubscriptionDTO> getPromoSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(promoSubscriptionService.getPromoSubscription(id));
    }

    @GetMapping("/promosubscription")
<<<<<<< subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestPromoSubscriptionController.java
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Получить все промоподписки.")
    public ResponseEntity<List<PromoSubscriptionDTO>> getPromoSubscriptions() {
=======
    @Operation(summary = "Получить все промоподписки.")
    public ResponseEntity<List<PromoSubscriptionDTO>> getPromoSubscription() {
>>>>>>> subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestPromoSubscriptionController.java
        return ResponseEntity.ok(promoSubscriptionService.getAllPromoSubscription());
    }

    @PostMapping("/promosubscription")
<<<<<<< subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestPromoSubscriptionController.java
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Создать промоподписку.")
=======
    @Operation(summary = "Создать промоподписку.")
>>>>>>> subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestPromoSubscriptionController.java
    public ResponseEntity<?> createPromoSubscription(@RequestBody PromoSubscriptionDTO promosubscriptionDTO) {
        promoSubscriptionService.createPromoSubscription(promosubscriptionDTO);
        return ResponseEntity.ok(promosubscriptionDTO);
    }

    @PutMapping("/promosubscription")
<<<<<<< subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestPromoSubscriptionController.java
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Изменить промоподписку.")
=======
    @Operation(summary = "Изменить промоподписку.")
>>>>>>> subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestPromoSubscriptionController.java
    public ResponseEntity<?> updatePromoSubscription(@RequestBody PromoSubscriptionDTO promosubscriptionDTO) {
        promoSubscriptionService.updatePromoSubscription(promosubscriptionDTO);
        return ResponseEntity.ok(promosubscriptionDTO);
    }

    @DeleteMapping("/promosubscription/{id}")
<<<<<<< subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestPromoSubscriptionController.java
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Удалить промоподписку.")
=======
    @Operation(summary = "Удалить промоподписку.")
>>>>>>> subscription/src/main/java/com/exchangeinformant/subscription/controllers/RestPromoSubscriptionController.java
    public ResponseEntity<?> deletePromoSubscription(@PathVariable Long id) {
        promoSubscriptionService.deletePromoSubscription(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
