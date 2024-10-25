package com.ferchau.spain.weather.domain.usecase.location;

import com.ferchau.spain.weather.domain.gateway.IRetrieveAllCities;
import com.ferchau.spain.weather.domain.gateway.ISearchCities;
import com.ferchau.spain.weather.domain.model.City;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SearchCities implements ISearchCities {

    private IRetrieveAllCities retrieveAllCities;

    @Override
    public List<City> execute(String queryName) {
        return retrieveAllCities.execute().stream()
                .filter(city -> city.getName().toLowerCase().contains(queryName))
                .collect(Collectors.toList());
    }
}
