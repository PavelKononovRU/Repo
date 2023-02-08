package com.exchangeinformat.userprofile.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Data {

    private final String title;
    private List<Violation> REQUIRED_PARAMETERS;


}
