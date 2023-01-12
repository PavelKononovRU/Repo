package com.exchangeinformant.util;

import lombok.Data;

/**
 * Created by e-davidenko
 * Date: 12.01.2023
 * Time: 16:20
 */

public enum ErrorCodes {

    BAD_GATEWAY("external_system_is_not_available"),
    BAD_RESPONSE("something_went_wrong");

    ErrorCodes(String error) {
    }
}
