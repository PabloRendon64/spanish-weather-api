package com.ferchau.spain.weather.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class WeatherPredictionResult {

    @JsonProperty("mediaTemperatura")
    private Double averageTemperature;

    @JsonProperty("unidadTemperatura")
    private String temperatureUnit;

    @JsonProperty("probPrecipitacion")
    private List<PrecipitationResult> precipitationProbability;

}
