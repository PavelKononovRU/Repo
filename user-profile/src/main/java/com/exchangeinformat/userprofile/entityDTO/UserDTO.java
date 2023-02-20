package com.exchangeinformat.userprofile.entityDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Builder
@Validated
@Data
@Mapper
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Пользователь", description = "Сущность пользователя")
public class UserDTO {

    @Schema(description = "Идентификатор пользователя")
    private Long id;

    @Schema(description = "Никнейм пользователя")
    @NotBlank(message = "Поле не должно быть пустым и не должно содержать пробелов")
    private String username;

    @Schema(description = "Идентификатор пользователя, назначенный keycloak'ом")
    @NotBlank(message = "Поле не должно быть пустым и не должно содержать пробелов")
    private String extId;

    @Schema(description = "Имя пользователя")
    @NotBlank(message = "Поле не должно быть пустым и не должно содержать пробелов")
    private String firstName;

    @Schema(description = "Фамилия пользователя")
    @NotBlank(message = "Поле не должно быть пустым и не должно содержать пробелов")
    private String lastName;

    @Schema(description = "Отчество пользователя")
    @NotBlank(message = "Поле не должно быть пустым и не должно содержать пробелов")
    private String middleName;

    @Schema(description = "Дата рождения пользователя")
    @Past(message = "Поле должно быть в прошедшем времени")
    private Date birthDay;

    @Schema(description = "Почта пользователя")
    @Email(message = "Введите правильный формат почты")
    private String email;

    @Schema(description = "Номер телефона пользователя", example = "89000000000")
    @Pattern(regexp = "^\\d{11}$") // Проверка, что телефон равен ровно 11 цифрам
    private String phone;

    @Schema(description = "Пол пользователя")
    @NotBlank(message = "Поле не должно быть пустым и не должно содержать пробелов")
    private String gender;

    @Schema(description = "Идентификационный номер налогоплательщика") //Это точно ИНН? уточнить
    @NotBlank(message = "Поле не должно быть пустым и не должно содержать пробелов")
    private String inn;

    @Schema(description = "Адрес пользователя")
    private AddressDTO address;

    @Schema(description = "Работа пользователя")
    private JobDTO job;
}
