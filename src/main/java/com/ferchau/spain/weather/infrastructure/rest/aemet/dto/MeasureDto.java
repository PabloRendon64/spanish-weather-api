package com.ferchau.spain.weather.infrastructure.rest.aemet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeasureDto {

    private String value;
    @JsonProperty("periodo")
    private String period;

}