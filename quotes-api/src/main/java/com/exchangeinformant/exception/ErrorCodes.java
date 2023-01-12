package com.exchangeinformant.exception;

import lombok.Getter;

/**
 * Created by e-davidenko
 * Date: 12.01.2023
 * Time: 16:20
 */
@Getter
public enum ErrorCodes {

    UPDATE_PROBLEM("external_system_is_not_available", "External systems can't process your request. Please try again later.");

    private final String errorCode;

    private final String errorMessage;

    ErrorCodes(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


}
