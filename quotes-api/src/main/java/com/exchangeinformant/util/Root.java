package com.exchangeinformant.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;


@Data
public class Root {
    @JsonProperty("data")
    private ArrayList<Name> name;
}
