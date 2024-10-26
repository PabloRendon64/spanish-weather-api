package com.ferchau.spain.weather.domain.usecase.location;

import com.ferchau.spain.weather.domain.gateway.IRetrieveAllCities;
import com.ferchau.spain.weather.domain.gateway.IRetrieveAllCitiesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.ferchau.spain.weather.BaseTest.mockCityResult;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RetrieveAllCitiesTest {

    IRetrieveAllCities retrieveAllCities;

    @MockBean
    IRetrieveAllCitiesRepository retrieveAllCitiesRepository;

    @BeforeEach
    public void setup() {
        retrieveAllCities = new RetrieveAllCities(retrieveAllCitiesRepository);
    }

    @Test
    @DisplayName("when repository responds ok then return all cities")
    void WhenRepositoryRespondsOkThenReturnAllCities() {
        //given
        var expectedResult = List.of(mockCityResult());
        //when
        when(retrieveAllCitiesRepository.execute()).thenReturn(expectedResult);
        //then
        var result = retrieveAllCities.execute();
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

}
