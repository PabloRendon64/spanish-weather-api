package com.ferchau.spain.weather.infrastructure.rest.aemet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ferchau.spain.weather.domain.exception.RepositoryException;
import com.ferchau.spain.weather.domain.gateway.IRetrieveWeatherPredictionRepository;
import com.ferchau.spain.weather.infrastructure.rest.aemet.mapper.IWeatherPredictionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static com.ferchau.spain.weather.BaseTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RetrieveWeatherPredictionRepositoryTest {

    IRetrieveWeatherPredictionRepository retrieveWeatherPredictionRepository;

    @MockBean
    IGetWeatherPredictionRepository getWeatherPredictionRepository;

    @MockBean
    IRetrieveDataRepository retrieveDataRepository;

    @MockBean
    IWeatherPredictionMapper weatherPredictionMapper;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @BeforeEach
    public void setup() {
        retrieveWeatherPredictionRepository = new RetrieveWeatherPredictionRepository(getWeatherPredictionRepository,
                retrieveDataRepository, weatherPredictionMapper, objectMapper);
    }

    @Test
    @DisplayName("given cityId when dependencies responds ok then return weather prediction")
    void givenCityIdWhenDependenciesRespondsOkThenReturnWeatherPrediction() {
        //given
        var cityId = TEST_CITY_ID;
        var weatherPredictionResponse = """
                [
                  {
                    "prediccion": {
                      "dia": [
                        {
                          "temperatura": [
                            {
                              "value": "22",
                              "periodo": "0208"
                            }
                          ],
                          "probTormenta": [
                            {
                              "value": "50",
                              "periodo": "0208"
                            }
                          ],
                          "fecha": "2022-10-26T00:00:00"
                        }
                      ]
                    }
                  }
                ]
                """;
        var date = LocalDate.of(2022, 10, 26).atStartOfDay();
        var weatherPredictions = List.of(mockWeatherPrediction().setDate(date));

        //when
        when(getWeatherPredictionRepository.execute(any())).thenReturn(mockGenericResponseDto());
        mockRetrieveDataRepository(retrieveDataRepository, weatherPredictionResponse);
        when(weatherPredictionMapper.toWeatherPredictions(any())).thenReturn(weatherPredictions);
        //then
        var result = retrieveWeatherPredictionRepository.execute(cityId);
        assertThat(result).usingRecursiveComparison().isEqualTo(weatherPredictions);
    }

    @Test
    @DisplayName("given cityId when dependencies fail then throw an exception")
    void givenCityIdWhenDependenciesFailsThenThrowAnException() {
        //given
        var cityId = TEST_CITY_ID;
        var weatherPredictionResponse = "fail";
        //when
        when(getWeatherPredictionRepository.execute(any())).thenReturn(mockGenericResponseDto());
        mockRetrieveDataRepository(retrieveDataRepository, weatherPredictionResponse);
        verifyNoInteractions(weatherPredictionMapper);
        //then
        assertThrows(RepositoryException.class, () -> retrieveWeatherPredictionRepository.execute(cityId));
    }

}
