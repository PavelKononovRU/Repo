package com.exchangeinformant.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created in IntelliJ
 * User: e-davidenko
 * Date: 22.12.2022
 * Time: 13:23
 */
@Data
public class Root {
    @JsonProperty("Global Quote")
    public Info alphaInfo;
}
