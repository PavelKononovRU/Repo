package com.exchangeinformant.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 12.01.2023
 * Time: 22:01
 */
@Data
@AllArgsConstructor
public class ExceptionBody {
        private String message;

        private String errorCode;

}
