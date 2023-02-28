package com.exchange.payingservice.controllers;

import com.exchange.payingservice.dto.PaymentDTO;
import com.exchange.payingservice.dto.StubPaymentDTO;
import com.exchange.payingservice.entity.Payment;
import com.exchange.payingservice.mappers.PaymentsMapper;
import com.exchange.payingservice.service.PaymentService;
import com.exchange.payingservice.util.PaymentStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Tag(name = "Контроллер платежей", description = "CRUD операции с платежами.")
@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "Получение платежа по идентификатору.")
    @GetMapping(("/{id}"))
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long id) {
        PaymentDTO paymentDTO = PaymentsMapper.INSTANCE.toDTO(paymentService.getPaymentById(id));
        return RestControllerAdvice.generatePaymentsResponse(paymentDTO);
    }

    @Operation(summary = "Получение всех платежей.")
    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayment());
    }

    @Operation(summary = "Изменение платежа по идентификатору.")
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updatePayment(@PathVariable @Parameter(description = "Идентификатор платежа.") Long id,
                                                    @Valid @RequestBody PaymentDTO paymentDTO) {
        paymentService.updatePayment(id, paymentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удаление платежа по идентификатору.")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePayment(@PathVariable @Parameter(description = "Идентификатор платежа.") Long id) {
        paymentService.deletePaymentById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Добавление нового платежа.")
    @PostMapping
    public ResponseEntity<PaymentStatus> createPayment(@Valid @RequestBody StubPaymentDTO stubPaymentDTO, Principal principal) {
        return paymentService.methodGetBodyToStubPayment(stubPaymentDTO, principal);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return RestControllerAdvice.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}