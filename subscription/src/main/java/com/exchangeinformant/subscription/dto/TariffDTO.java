package com.exchangeinformant.subscription.dto;

import lombok.Data;
import org.mapstruct.Mapper;


@Data
@Mapper
public class TariffDTO {
    private Long id;
    private String title;
    private String description;
    private String type;
    private Boolean isActive;
}
