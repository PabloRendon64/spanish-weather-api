package com.ferchau.spain.weather.infrastructure.rest.aemet;

import com.ferchau.spain.weather.infrastructure.rest.aemet.dto.GenericResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("get-cities")
public interface IGetCitiesRepository {

    @GetMapping
    GenericResponseDto execute();

}
