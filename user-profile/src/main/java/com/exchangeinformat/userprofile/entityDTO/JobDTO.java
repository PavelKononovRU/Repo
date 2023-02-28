package com.exchangeinformat.userprofile.entityDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mapstruct.Mapper;

@Data
@Mapper
@Schema(name = "Работа пользователя", description = "Информация о работе пользователя")
public class JobDTO {

    @Schema(description = "Идентификатор квартиры")
    private Long id;

    @Schema(description = "Наименование организации")
    private String title;

    @Schema(description = "Должность")
    private String position;

}
