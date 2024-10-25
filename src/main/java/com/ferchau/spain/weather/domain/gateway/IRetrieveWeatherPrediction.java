package com.ferchau.spain.weather.domain.gateway;

import com.ferchau.spain.weather.domain.model.WeatherPrediction;

public interface IRetrieveWeatherPrediction {

    WeatherPrediction execute(String cityId, String temperatureUnit);

}
