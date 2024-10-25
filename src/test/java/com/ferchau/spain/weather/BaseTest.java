package com.ferchau.spain.weather;

import com.ferchau.spain.weather.domain.model.Measure;
import com.ferchau.spain.weather.domain.model.WeatherPrediction;

import java.time.OffsetDateTime;
import java.util.List;

public class BaseTest {

    public static String TEST_CITY_ID = "27021";
    public static String TEST_TEMPERATURE_UNIT_CELSIUS = "G_CEL";

    public static WeatherPrediction mockWeatherPrediction() {
        return new WeatherPrediction()
                .setDate(OffsetDateTime.now().plusDays(1))
                .setTemperature(List.of(mockMeasureTemperature()))
                .setPrecipitationProbability(List.of(mockMeasurePrecipitationProbability()));
    }

    public static Measure mockMeasureTemperature() {
        return new Measure()
                .setPeriod("0814")
                .setValue(5L);
    }

    public static Measure mockMeasurePrecipitationProbability() {
        return new Measure()
                .setPeriod("0814")
                .setValue(50L);
    }

}
