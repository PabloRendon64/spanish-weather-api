package com.ferchau.spain.weather.infrastructure.rest.aemet.mapper;

import com.ferchau.spain.weather.domain.model.City;
import com.ferchau.spain.weather.infrastructure.rest.aemet.dto.CityDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ICityMapper {

    City toCity(CityDto cityDto);

    List<City> toCities(List<CityDto> citiesDto);

}
