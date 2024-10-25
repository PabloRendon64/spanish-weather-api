package com.ferchau.spain.weather.domain.gateway;

import com.ferchau.spain.weather.domain.model.WeatherPredictionResult;

public interface IRetrieveWeatherPrediction {

    WeatherPredictionResult execute(String cityId, String temperatureUnit);

}
