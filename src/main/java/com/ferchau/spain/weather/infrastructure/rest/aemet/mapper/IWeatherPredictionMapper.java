package com.ferchau.spain.weather.infrastructure.rest.aemet.mapper;

import com.ferchau.spain.weather.domain.model.WeatherPrediction;
import com.ferchau.spain.weather.infrastructure.rest.aemet.dto.WeatherPredictionDayDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IWeatherPredictionMapper {

    WeatherPrediction toWeatherPrediction(WeatherPredictionDayDto weatherPredictionDto);

    List<WeatherPrediction> toWeatherPredictions(List<WeatherPredictionDayDto> weatherPredictionsDto);

    default OffsetDateTime map(Date value) {
        return value.toInstant().atOffset(ZoneOffset.UTC);
    }

}
