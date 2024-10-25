package com.ferchau.spain.weather.domain.usecase.weather;

import com.ferchau.spain.weather.domain.gateway.IRetrieveWeatherPrediction;
import com.ferchau.spain.weather.domain.gateway.IRetrieveWeatherPredictionRepository;
import com.ferchau.spain.weather.domain.model.WeatherPrediction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RetrieveWeatherPrediction implements IRetrieveWeatherPrediction {

    private IRetrieveWeatherPredictionRepository retrieveWeatherPredictionRepository;

    @Override
    public WeatherPrediction execute(String cityId, String temperatureUnit) {
        var response = retrieveWeatherPredictionRepository.execute(cityId);
        return null;
    }
}
