package com.ferchau.spain.weather;

import com.ferchau.spain.weather.domain.model.*;
import com.ferchau.spain.weather.infrastructure.rest.aemet.IRetrieveDataRepository;
import com.ferchau.spain.weather.infrastructure.rest.aemet.dto.GenericResponseDto;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BaseTest {

    public static String TEST_CITY_ID = "27021";

    public static String TEST_QUERY_NAME = "val";
    public static String TEST_CITY_NAME = "Valencia";
    public static String TEST_TEMPERATURE_UNIT_CELSIUS = "G_CEL";
    public static Long TEST_TEMPERATURE_MEASURE = 22L;
    public static Double TEST_AVERAGE_TEMPERATURE = 22.00D;
    public static String TEST_PERIOD = "02-08";
    public static String TEST_UNFORMATTED_PERIOD = "0208";
    public static int TEST_PRECIPITATION_PROBABILITY = 50;

    public static WeatherPrediction mockWeatherPrediction() {
        return new WeatherPrediction()
                .setDate(LocalDate.now().atStartOfDay().plusDays(1))
                .setTemperature(List.of(mockMeasureTemperature()))
                .setPrecipitationProbability(List.of(mockMeasurePrecipitationProbability()));
    }

    public static Measure mockMeasureTemperature() {
        return new Measure()
                .setPeriod(TEST_UNFORMATTED_PERIOD)
                .setValue(TEST_TEMPERATURE_MEASURE);
    }

    public static Measure mockMeasurePrecipitationProbability() {
        return new Measure()
                .setPeriod(TEST_UNFORMATTED_PERIOD)
                .setValue((long) TEST_PRECIPITATION_PROBABILITY);
    }

    public static WeatherPredictionResult mockWeatherPredictionResult() {
        return new WeatherPredictionResult()
                .setAverageTemperature(TEST_AVERAGE_TEMPERATURE)
                .setTemperatureUnit(TEST_TEMPERATURE_UNIT_CELSIUS)
                .setPrecipitationProbability(List.of(mockPrecipitationResult()));
    }

    public static PrecipitationResult mockPrecipitationResult() {
        return new PrecipitationResult()
                .setPeriod(TEST_PERIOD)
                .setProbability(TEST_PRECIPITATION_PROBABILITY);
    }

    public static City mockCityResult() {
        return new City().setId(TEST_CITY_ID).setName(TEST_CITY_NAME);
    }

    public static GenericResponseDto mockGenericResponseDto() {
        return new GenericResponseDto()
                .setData("");
    }

    public static void mockRetrieveDataRepository(IRetrieveDataRepository retrieveDataRepository, String response) {
        when(retrieveDataRepository.execute(any())).thenReturn(response);
    }

}
