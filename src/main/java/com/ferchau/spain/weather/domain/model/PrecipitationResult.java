package com.ferchau.spain.weather.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PrecipitationResult {

    @JsonProperty("probabilidad")
    private Integer probability;
    @JsonProperty("periodo")
    private String period;

}
