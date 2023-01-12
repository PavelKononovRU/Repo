package com.exchangeinformant.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by e-davidenko
 * Date: 12.01.2023
 * Time: 15:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class QuotesUpdateException extends RuntimeException {

    private String message;


}
