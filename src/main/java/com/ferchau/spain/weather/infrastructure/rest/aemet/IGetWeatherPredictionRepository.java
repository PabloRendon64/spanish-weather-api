package com.ferchau.spain.weather.infrastructure.rest.aemet;

import com.ferchau.spain.weather.infrastructure.rest.aemet.dto.GenericResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "get-weather-prediction")
public interface IGetWeatherPredictionRepository {

    @GetMapping(value = "/{city_id}")
    GenericResponseDto execute(@PathVariable("city_id") String cityId);

}
