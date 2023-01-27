package com.exchangeinformant.subscription.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

@Data
@Mapper
@Validated
public class TariffDTO {
    private Long id;
    @NotNull(message = "title shouldn't be null")
    private String title;
    @NotNull(message = "description shouldn't be null")
    private String description;
    @NotNull(message = "type shouldn't be null")
    private String type;
    private Boolean isActive;
}
