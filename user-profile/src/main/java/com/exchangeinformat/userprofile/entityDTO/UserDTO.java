package com.exchangeinformat.userprofile.entityDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Validated
@Data
@Mapper
public class UserDTO {

    private Long id;

    @NotBlank(message = "Поле не должно быть пустым и не должно содержать пробелов")
    private String username;

    @NotBlank(message = "Поле не должно быть пустым и не должно содержать пробелов")
    private String extId;

    @NotBlank(message = "Поле не должно быть пустым и не должно содержать пробелов")
    private String firstName;

    @NotBlank(message = "Поле не должно быть пустым и не должно содержать пробелов")
    private String lastName;

    @NotBlank(message = "Поле не должно быть пустым и не должно содержать пробелов")
    private String middleName;

    @Past(message = "Поле должно быть в прошедшем времени")
    private Date birthDay;

    @Email(message = "Введите правильный формат почты")
    private String email;

    @Pattern(regexp = "^\\d{11}$") // Проверка, что телефон равен ровно 11 цифрам
    private String phone;

    @NotBlank(message = "Поле не должно быть пустым и не должно содержать пробелов")
    private String gender;

    @NotBlank(message = "Поле не должно быть пустым и не должно содержать пробелов")
    private String inn;


}
