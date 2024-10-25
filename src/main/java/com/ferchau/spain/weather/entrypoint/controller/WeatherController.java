package com.ferchau.spain.weather.entrypoint.controller;

import com.ferchau.spain.weather.domain.gateway.IRetrieveAllCities;
import com.ferchau.spain.weather.entrypoint.controller.contracts.CitiesResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/weather/")
@AllArgsConstructor
public class WeatherController implements IWeatherApi {

    private final IRetrieveAllCities retrieveAllCities;

    @Override
    @GetMapping("/retrieve-all-cities")
    public ResponseEntity<CitiesResponse> retrieveAllCitiesOperation() {
        return ResponseEntity.ok(new CitiesResponse().setContent(retrieveAllCities.execute()));
    }

}
