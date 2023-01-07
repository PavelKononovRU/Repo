package com.exchangeinformant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 22.12.2022
 * Time: 13:23
 */
@Data
public class Root {
    @JsonProperty("data")
    public List<Stock> stocks;

    public int lastValue;
    public int total;
    public boolean hasMore;
}
