package com.ferchau.spain.weather.infrastructure.rest.aemet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CityMasterDataDto {

    @JsonProperty("datos")
    private String data;

}
