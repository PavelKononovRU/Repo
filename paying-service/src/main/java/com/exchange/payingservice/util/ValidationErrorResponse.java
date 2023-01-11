package com.exchange.payingservice.util;

import com.exchange.payingservice.dto.StatusCards;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ValidationErrorResponse {
    private final List<StatusCards> violations;
}
