package com.exchangeinformat.userprofile.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Data {

    private final String ERROR = "Данные не прошли валидацию";
    private final List<Violation> REQUIRED_PARAMETERS;


}
