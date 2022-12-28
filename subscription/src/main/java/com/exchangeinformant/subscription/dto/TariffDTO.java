package com.exchangeinformant.subscription.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

import java.util.Set;

//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Data
@Mapper
public class TariffDTO {
    private Long id;
    private String title;
    private String description;
    private String type;
    private Boolean isActive;
}
