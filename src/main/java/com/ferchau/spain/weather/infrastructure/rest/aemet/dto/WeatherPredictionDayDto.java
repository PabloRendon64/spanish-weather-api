package com.ferchau.spain.weather.infrastructure.rest.aemet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherPredictionDayDto {

    @JsonProperty("probPrecipitacion")
    private List<MeasureDto> precipitationProbability;
    @JsonProperty("temperatura")
    private List<MeasureDto> temperature;
    @JsonProperty("fecha")
    private LocalDateTime date;

}
