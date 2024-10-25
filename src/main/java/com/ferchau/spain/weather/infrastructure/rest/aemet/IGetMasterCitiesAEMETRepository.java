package com.ferchau.spain.weather.infrastructure.rest.aemet;

import com.ferchau.spain.weather.infrastructure.rest.aemet.dto.CityMasterDataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("get-master-cities")
public interface IGetMasterCitiesAEMETRepository {

    @GetMapping
    CityMasterDataDto execute();

}
