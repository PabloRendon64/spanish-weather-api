package com.ferchau.spain.weather.infrastructure.rest.aemet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferchau.spain.weather.domain.exception.RepositoryException;
import com.ferchau.spain.weather.domain.gateway.IRetrieveAllCitiesRepository;
import com.ferchau.spain.weather.domain.model.City;
import com.ferchau.spain.weather.infrastructure.rest.aemet.dto.CityDto;
import com.ferchau.spain.weather.infrastructure.rest.aemet.mapper.ICityMapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
public class RetrieveAllCitiesRepository implements IRetrieveAllCitiesRepository {

    private IGetCitiesRepository getCitiesRepository;
    private IRetrieveDataRepository retrieveDataRepository;
    private ICityMapper cityMapper;
    private ObjectMapper objectMapper;

    @Override
    @Cacheable(cacheNames = "retrieve-all-cities-cache")
    public List<City> execute() {
        var data = getCitiesRepository.execute();
        URI determinedBasePathUri = URI.create(data.getData());

        var response = retrieveDataRepository.execute(determinedBasePathUri);

        List<CityDto> cityDtoList;
        try {
            cityDtoList = objectMapper.readValue(response, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new RepositoryException(e.getMessage());
        }
        return cityMapper.toCities(cityDtoList);
    }
}
