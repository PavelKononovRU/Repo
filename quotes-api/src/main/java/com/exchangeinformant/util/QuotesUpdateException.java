package com.exchangeinformant.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by e-davidenko
 * Date: 12.01.2023
 * Time: 15:37
 */
@Data
@AllArgsConstructor
public class QuotesUpdateException extends RuntimeException {

    private String message;
}
