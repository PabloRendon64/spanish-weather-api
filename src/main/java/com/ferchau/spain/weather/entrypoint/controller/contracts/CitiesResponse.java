package com.ferchau.spain.weather.entrypoint.controller.contracts;

import com.ferchau.spain.weather.domain.model.City;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CitiesResponse {

    private List<City> content;

}
