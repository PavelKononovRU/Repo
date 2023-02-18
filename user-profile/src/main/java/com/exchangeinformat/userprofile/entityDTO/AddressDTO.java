package com.exchangeinformat.userprofile.entityDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mapstruct.Mapper;

@Data
@Mapper
@Schema(name = "Адрес пользователя", description = "Информация о адресе проживания пользователя")
public class AddressDTO {

    @Schema(description = "Идентификатор адреса")
    private Long id;

    @Schema(description = "Регион проживания")
    private String region;

    @Schema(description = "Город проживания")
    private String city;

    @Schema(description = "Улица проживания")
    private String street;

    @Schema(description = "Номер дома")
    private String house;

    @Schema(description = "Номер квартиры")
    private String flat;
}
