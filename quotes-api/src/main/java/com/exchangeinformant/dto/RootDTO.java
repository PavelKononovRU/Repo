package com.exchangeinformant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;


@Data
public class RootDTO {
    @JsonProperty("data")
    private LinkedList<NameDTO> nameDTO;
}
