package com.exchange.payingservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Schema(name = "Карта", description = "Сущность карты") //for swagger
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDTO {

    @Schema(description = "Идентификатор карты")
    private Long id;

    @Schema(description = "Номер карты")
    @Pattern(regexp = "([2-6]([0-9]{3})-?)(([0-9]{4}-?){3})",message = "Некорректный ввод номера карты.")
    private String number;

    @Schema(description = "Владелец карты")
    @NotBlank
    @Length(min = 2)
    private String principal;

    @Schema(description = "CSV код карты")
    @Pattern(regexp = "[0-9]{3}",message = "Некорректный CVV код. Код должен состоять из 3 цифр.")
    private String CSV;

    @Schema(description = "Идентификатор пользователя")
    @NotNull
    private Long user_id;

    public CardDTO(String number, String principal, String CSV, Long user_id) {
        this.number = number;
        this.principal = principal;
        this.CSV = CSV;
        this.user_id = user_id;
    }
}
