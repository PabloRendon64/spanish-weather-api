package com.ferchau.spain.weather.domain.usecase;

import com.ferchau.spain.weather.BaseTest;
import com.ferchau.spain.weather.domain.gateway.IRetrieveWeatherPrediction;
import com.ferchau.spain.weather.domain.gateway.IRetrieveWeatherPredictionRepository;
import com.ferchau.spain.weather.domain.usecase.weather.RetrieveWeatherPrediction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

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
    @DisplayName("given cityId and temperatureUnit in celsius when repository respond ok then return weather prediction result")
    void givenBrandIdAndProductIdAndQueryDateThenReturnPriceResult() {
        //given
        var cityId = TEST_CITY_ID;
        var temperatureUnit = TEST_TEMPERATURE_UNIT_CELSIUS;
        //when
        when(retrieveWeatherPredictionRepository.execute(anyString()))
                .thenReturn(List.of(mockWeatherPrediction()));
        //then
        var result = retrieveWeatherPrediction.execute(cityId, temperatureUnit);
        //assertThat(result).usingRecursiveComparison().isEqualTo(null);
    }

}
