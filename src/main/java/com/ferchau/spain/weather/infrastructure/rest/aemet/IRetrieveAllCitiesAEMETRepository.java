package com.ferchau.spain.weather.infrastructure.rest.aemet;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(value = "retrieve-all-cities")
public interface IRetrieveAllCitiesAEMETRepository {

    @GetMapping
    String execute(URI baseUrl);

}
