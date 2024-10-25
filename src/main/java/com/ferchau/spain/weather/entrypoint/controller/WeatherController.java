package com.ferchau.spain.weather.entrypoint.controller;

import com.ferchau.spain.weather.domain.gateway.IRetrieveWeatherPrediction;
import com.ferchau.spain.weather.domain.gateway.ISearchCities;
import com.ferchau.spain.weather.domain.model.WeatherPredictionResult;
import com.ferchau.spain.weather.entrypoint.controller.contracts.CitiesResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/weather/")
@AllArgsConstructor
public class WeatherController implements IWeatherApi {

    private final ISearchCities searchCities;
    private final IRetrieveWeatherPrediction retrieveWeatherPrediction;

    @Override
    @GetMapping("/search-cities")
    public ResponseEntity<CitiesResponse> searchCitiesOperation(@RequestParam("query_name") String queryName) {
        return ResponseEntity.ok(new CitiesResponse().setContent(searchCities.execute(queryName)));
    }

    @Override
    @GetMapping("/prediction")
    public ResponseEntity<WeatherPredictionResult> getWeatherPredictionOperation(
            @RequestParam("city_id") String cityId,
            @RequestParam("temperature_unit") String temperatureUnit) {
        return ResponseEntity.ok(retrieveWeatherPrediction.execute(cityId, temperatureUnit));
    }

}
