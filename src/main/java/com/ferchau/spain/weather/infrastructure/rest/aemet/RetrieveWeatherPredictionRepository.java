package com.ferchau.spain.weather.infrastructure.rest.aemet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferchau.spain.weather.domain.gateway.IRetrieveWeatherPredictionRepository;
import com.ferchau.spain.weather.domain.model.WeatherPrediction;
import com.ferchau.spain.weather.infrastructure.rest.aemet.dto.WeatherPredictionResponseDto;
import com.ferchau.spain.weather.infrastructure.rest.aemet.mapper.IWeatherPredictionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
public class RetrieveWeatherPredictionRepository implements IRetrieveWeatherPredictionRepository {

    private IGetWeatherPredictionRepository getWeatherPredictionRepository;
    private IRetrieveDataRepository retrieveDataRepository;
    private IWeatherPredictionMapper weatherPredictionMapper;

    @Override
    public List<WeatherPrediction> execute(String cityId) {
        var data = getWeatherPredictionRepository.execute(cityId);
        URI determinedBasePathUri = URI.create(data.getData());

        var response = retrieveDataRepository.execute(determinedBasePathUri);
        ObjectMapper mapper = new ObjectMapper();
        List<WeatherPredictionResponseDto> weatherPredictionDtoList;
        try {
            weatherPredictionDtoList = mapper.readValue(response, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return weatherPredictionMapper.toWeatherPredictions(weatherPredictionDtoList.getFirst().getPrediction().getDay());
    }
}
