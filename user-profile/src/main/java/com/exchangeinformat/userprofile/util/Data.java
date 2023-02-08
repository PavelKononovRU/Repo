package com.exchangeinformat.userprofile.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Data {

    private final List<ValidationErrorResponse> required_parametrs;
}
