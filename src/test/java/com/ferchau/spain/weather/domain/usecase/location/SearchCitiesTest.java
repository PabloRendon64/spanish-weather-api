package com.ferchau.spain.weather.domain.usecase.location;

import com.ferchau.spain.weather.domain.gateway.IRetrieveAllCities;
import com.ferchau.spain.weather.domain.gateway.ISearchCities;
import com.ferchau.spain.weather.domain.model.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.ferchau.spain.weather.BaseTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SearchCitiesTest {

    ISearchCities searchCities;

    @MockBean
    IRetrieveAllCities retrieveAllCities;

    @BeforeEach
    public void setup() {
        searchCities = new SearchCities(retrieveAllCities);
    }

    @Test
    @DisplayName("given query name param to search when retrieveAllCities responds ok then return search results")
    void givenQueryNameParamToSearchWhenRetrieveAllCitiesRespondsOkThenReturnSearchResults() {
        //given
        var city1 = new City().setId("id3025").setName("random");
        var city2 = new City().setId("id27021").setName(TEST_CITY_NAME);
        var expectedResult = List.of(mockCityResult());
        //when
        when(retrieveAllCities.execute()).thenReturn(List.of(city1, city2));
        //then
        var result = searchCities.execute(TEST_QUERY_NAME);
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

}
