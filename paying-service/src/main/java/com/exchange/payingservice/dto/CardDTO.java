package com.exchange.payingservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDTO {
    private Long id;
    @Pattern(regexp = "([2-6]([0-9]{3})-?)(([0-9]{4}-?){3})",message = "Некорректный ввод номера карты.")
    private String number;
    @NotBlank
    @Length(min = 2)
    private String principal;
    @Pattern(regexp = "[0-9]{3}",message = "Некорректный CVV код. Код должен состоять из 3 цифр.")
    private String CSV;
    @NotNull
    private Long user_id;

    public CardDTO(Long id, String number, String principal, String CSV, Long user_id) {
        this.id = id;
        this.number = number;
        this.principal = principal;
        this.CSV = CSV;
        this.user_id = user_id;
    }
}
