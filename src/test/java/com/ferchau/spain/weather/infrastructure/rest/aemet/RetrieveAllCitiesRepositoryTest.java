package com.ferchau.spain.weather.infrastructure.rest.aemet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferchau.spain.weather.domain.exception.RepositoryException;
import com.ferchau.spain.weather.domain.gateway.IRetrieveAllCitiesRepository;
import com.ferchau.spain.weather.domain.model.City;
import com.ferchau.spain.weather.infrastructure.rest.aemet.mapper.ICityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.ferchau.spain.weather.BaseTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RetrieveAllCitiesRepositoryTest {

    IRetrieveAllCitiesRepository retrieveAllCitiesRepository;

    @MockBean
    IGetCitiesRepository getCitiesRepository;

    @MockBean
    IRetrieveDataRepository retrieveDataRepository;

    @MockBean
    ICityMapper cityMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        retrieveAllCitiesRepository = new RetrieveAllCitiesRepository(getCitiesRepository, retrieveDataRepository, cityMapper, objectMapper);
    }

    @Test
    @DisplayName("when dependencies responds ok then return all cities")
    void whenDependenciesRespondsOkThenReturnAllCities() {
        //given
        var cityResponse = """
                [
                  {
                    "latitud": "40º54'58.824504\\"",
                    "nombre": "Ababuj",
                    "id": "id44001"
                  },
                  {
                    "latitud": "40º54'58.824504\\"",
                    "nombre": "Abades",
                    "id": "id40001"
                  }
                ]""";

        var city1 = new City().setId("id3025").setName("random");
        var city2 = new City().setId("id27021").setName(TEST_CITY_NAME);
        var cityList = List.of(city1, city2);
        //when
        when(getCitiesRepository.execute()).thenReturn(mockGenericResponseDto());
        mockRetrieveDataRepository(retrieveDataRepository, cityResponse);
        when(cityMapper.toCities(any())).thenReturn(cityList);
        //then
        var result = retrieveAllCitiesRepository.execute();
        assertThat(result).usingRecursiveComparison().isEqualTo(cityList);
    }

    @Test
    @DisplayName("when dependencies fail then throw an exception")
    void whenDependenciesFailsThenThrowAnException() {
        //given
        var cityResponse = "fail";
        //when
        when(getCitiesRepository.execute()).thenReturn(mockGenericResponseDto());
        mockRetrieveDataRepository(retrieveDataRepository, cityResponse);
        verifyNoInteractions(cityMapper);
        //then
        assertThrows(RepositoryException.class, () -> retrieveAllCitiesRepository.execute());
    }

}
