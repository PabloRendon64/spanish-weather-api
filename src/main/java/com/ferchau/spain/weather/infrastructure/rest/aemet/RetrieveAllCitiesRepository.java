package com.ferchau.spain.weather.infrastructure.rest.aemet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferchau.spain.weather.domain.gateway.IRetrieveAllCitiesRepository;
import com.ferchau.spain.weather.domain.model.City;
import com.ferchau.spain.weather.infrastructure.rest.aemet.dto.CityDto;
import com.ferchau.spain.weather.infrastructure.rest.aemet.mapper.ICityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
public class RetrieveAllCitiesRepository implements IRetrieveAllCitiesRepository {

    private IGetMasterCitiesAEMETRepository getMasterCitiesAEMETRepository;
    private IRetrieveAllCitiesAEMETRepository retrieveAllCitiesAEMETRepository;
    private ICityMapper iCityMapper;

    @Override
    public List<City> execute() {
        var data = getMasterCitiesAEMETRepository.execute();
        URI determinedBasePathUri = URI.create(data.getData());

        var response = retrieveAllCitiesAEMETRepository.execute(determinedBasePathUri);
        ObjectMapper mapper = new ObjectMapper();
        List<CityDto> cityDtoList;
        try {
            cityDtoList = mapper.readValue(response, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return iCityMapper.toCities(cityDtoList);
    }
}