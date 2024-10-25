package com.ferchau.spain.weather.domain.gateway;

import com.ferchau.spain.weather.domain.model.WeatherPrediction;

import java.util.List;

public interface IRetrieveWeatherPredictionRepository {

    List<WeatherPrediction> execute(String cityId);

}
