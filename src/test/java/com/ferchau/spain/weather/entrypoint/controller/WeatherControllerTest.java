package com.ferchau.spain.weather.entrypoint.controller;

import com.ferchau.spain.weather.BaseTest;
import com.ferchau.spain.weather.domain.gateway.IRetrieveWeatherPrediction;
import com.ferchau.spain.weather.domain.gateway.ISearchCities;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IWeatherApi.class)
class WeatherControllerTest extends BaseTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ISearchCities searchCities;

    @MockBean
    IRetrieveWeatherPrediction retrieveWeatherPrediction;

    @Test
    @DisplayName("given query name param when call url /api/cities/search then return http status ok")
    void searchCitiesOperationTestWhenResponseOk() throws Exception {
        when(searchCities.execute(any())).thenReturn(List.of(mockCityResult()));

        var queryName = TEST_QUERY_NAME;

        this.mockMvc.perform(get("/api/cities/search")
                        .queryParam("query_name", queryName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(TEST_CITY_ID))
                .andExpect(jsonPath("$.content[0].name").value(TEST_CITY_NAME));
    }

    @Test
    @DisplayName("given incomplete query params when call url /api/cities/search then return http status bad request")
    void searchCitiesOperationTestWhenResponseBadRequest() throws Exception {
        verifyNoInteractions(searchCities);

        this.mockMvc.perform(get("/api/cities/search"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("given city id and temperature unit params when call url /api/weather/prediction then return http status ok")
    void getWeatherPredictionOperationTestWhenResponseOk() throws Exception {
        when(retrieveWeatherPrediction.execute(any(), any())).thenReturn(mockWeatherPredictionResult());

        var cityId = TEST_CITY_ID;
        var temperatureUnit = TEST_TEMPERATURE_UNIT_CELSIUS;

        this.mockMvc.perform(get("/api/weather/prediction")
                        .queryParam("city_id", cityId)
                        .queryParam("temperature_unit", temperatureUnit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.unidadTemperatura").value(TEST_TEMPERATURE_UNIT_CELSIUS))
                .andExpect(jsonPath("$.mediaTemperatura").value(TEST_AVERAGE_TEMPERATURE));
    }

    @Test
    @DisplayName("given incomplete query params when call url /api/weather/prediction then return http bad request")
    void getWeatherPredictionOperationTestWhenResponseBadRequest() throws Exception {
        verifyNoInteractions(retrieveWeatherPrediction);

        var cityId = TEST_CITY_ID;

        this.mockMvc.perform(get("/api/weather/prediction")
                        .queryParam("city_id", cityId))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("when call wrong url then return http status not found")
    void weatherControllerWhenResponseNotFound() throws Exception {
        verifyNoInteractions(searchCities, retrieveWeatherPrediction);

        this.mockMvc.perform(get("/api/another-not-implemented-operation"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
