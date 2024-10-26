package com.ferchau.spain.weather.infrastructure.rest.aemet.mapper;

import com.ferchau.spain.weather.domain.model.WeatherPrediction;
import com.ferchau.spain.weather.infrastructure.rest.aemet.dto.WeatherPredictionDayDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IWeatherPredictionMapper {

    WeatherPrediction toWeatherPrediction(WeatherPredictionDayDto weatherPredictionDto);

    List<WeatherPrediction> toWeatherPredictions(List<WeatherPredictionDayDto> weatherPredictionsDto);

}
