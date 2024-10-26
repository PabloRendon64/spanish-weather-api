package com.ferchau.spain.weather.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class WeatherPrediction {

    private List<Measure> precipitationProbability;
    private List<Measure> temperature;
    private LocalDateTime date;

}
