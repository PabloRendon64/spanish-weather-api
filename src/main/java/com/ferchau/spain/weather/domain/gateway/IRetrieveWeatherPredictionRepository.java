package com.ferchau.spain.weather.domain.gateway;

import com.ferchau.spain.weather.domain.model.WeatherPrediction;

public interface IRetrieveWeatherPredictionRepository {

    WeatherPrediction execute(String cityId);

}
