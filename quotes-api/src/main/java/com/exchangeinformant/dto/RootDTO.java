package com.exchangeinformant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;


@Data
public class RootDTO {
    @JsonProperty("data")
    private ArrayList<NameDTO> nameDTO;
}
