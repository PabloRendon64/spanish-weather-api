package com.ferchau.spain.weather.domain.gateway;

import com.ferchau.spain.weather.domain.model.City;

import java.util.List;

public interface IRetrieveAllCities {

    List<City> execute();

}
