package com.exchangeinformat.userprofile.entityDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mapstruct.Mapper;

import java.sql.Date;

@Data
@Mapper
@Schema(name = "Продукт пользователя", description = "Информация о продукте пользователя")
public class ProductDTO {

    @Schema(description = "Идентификатор продукта")
    private Long id;

    @Schema(description = "Тип продукта")
    private String type;

    @Schema(description = "Наименование продукта")
    private String title;

    @Schema(description = "Дата создания продукта")
    private Date createdAt;

    @Schema(description = "Дата обновления продукта")
    private Date updatedAt;

    @Schema(description = "Цена продукта")
    private Double price;

    @Schema(description = "Это я хз что такое, прошу дать комментарий по этому поводу")
    private Date experiesedAt;
}
