package com.ferchau.spain.weather.domain.usecase.location;

import com.ferchau.spain.weather.domain.gateway.IRetrieveAllCities;
import com.ferchau.spain.weather.domain.gateway.IRetrieveAllCitiesRepository;
import com.ferchau.spain.weather.domain.model.City;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RetrieveAllCities implements IRetrieveAllCities {

    private IRetrieveAllCitiesRepository retrieveAllCitiesRepository;

    @Override
    public List<City> execute() {
        return retrieveAllCitiesRepository.execute();
    }
}
