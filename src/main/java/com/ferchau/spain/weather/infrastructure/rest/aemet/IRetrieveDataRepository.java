package com.ferchau.spain.weather.infrastructure.rest.aemet;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(value = "retrieve-data")
public interface IRetrieveDataRepository {

    @GetMapping
    String execute(URI baseUrl);

}
