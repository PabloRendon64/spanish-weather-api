package com.ferchau.spain.weather.domain.usecase.weather;

import com.ferchau.spain.weather.BaseTest;
import com.ferchau.spain.weather.domain.gateway.IRetrieveWeatherPrediction;
import com.ferchau.spain.weather.domain.gateway.IRetrieveWeatherPredictionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RetrieveWeatherPredictionTest extends BaseTest {

    IRetrieveWeatherPrediction retrieveWeatherPrediction;

    @MockBean
    IRetrieveWeatherPredictionRepository retrieveWeatherPredictionRepository;

    @BeforeEach
    public void setup() {
        retrieveWeatherPrediction = new RetrieveWeatherPrediction(retrieveWeatherPredictionRepository);
    }

    @Test
    @DisplayName("given cityId and temperatureUnit null when repository responds ok then return weather prediction result")
    void givenCityIdAndTemperatureUnitInCelsiusWhenRepositoryRespondsOkThenReturnWeatherPredictionResult() {
        //given
        var cityId = TEST_CITY_ID;
        var expectedResult = mockWeatherPredictionResult();
        //when
        when(retrieveWeatherPredictionRepository.execute(anyString()))
                .thenReturn(List.of(mockWeatherPrediction()));
        //then
        var result = retrieveWeatherPrediction.execute(cityId, null);
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("given cityId and temperatureUnit in fahrenheit when repository respond ok then return weather prediction result")
    void givenCityIdAndTemperatureUnitInFahrenheitWhenRepositoryRespondsOkThenReturnWeatherPredictionResult() {
        //given
        var cityId = TEST_CITY_ID;
        var temperatureUnit = RetrieveWeatherPrediction.TEMPERATURE_UNIT_FAHRENHEIT;
        var expectedResult = mockWeatherPredictionResult()
                .setAverageTemperature(71.6)
                .setTemperatureUnit(RetrieveWeatherPrediction.TEMPERATURE_UNIT_FAHRENHEIT);
        //when
        when(retrieveWeatherPredictionRepository.execute(anyString()))
                .thenReturn(List.of(mockWeatherPrediction()));
        //then
        var result = retrieveWeatherPrediction.execute(cityId, temperatureUnit);
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("given cityId and temperatureUnit when repository respond only today prediction then return weather today prediction result")
    void givenCityIdAndTemperatureUnitWhenRepositoryRespondsOkThenReturnWeatherPredictionResult() {
        //given
        var cityId = TEST_CITY_ID;
        var temperatureUnit = TEST_TEMPERATURE_UNIT_CELSIUS;
        var mockWeatherPrediction = mockWeatherPrediction()
                .setDate(LocalDateTime.now());
        var expectedResult = mockWeatherPredictionResult();
        //when
        when(retrieveWeatherPredictionRepository.execute(anyString()))
                .thenReturn(List.of(mockWeatherPrediction));
        //then
        var result = retrieveWeatherPrediction.execute(cityId, temperatureUnit);
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

}
