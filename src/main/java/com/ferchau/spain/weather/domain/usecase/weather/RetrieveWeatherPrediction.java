package com.ferchau.spain.weather.domain.usecase.weather;

import com.ferchau.spain.weather.domain.gateway.IRetrieveWeatherPrediction;
import com.ferchau.spain.weather.domain.gateway.IRetrieveWeatherPredictionRepository;
import com.ferchau.spain.weather.domain.model.Measure;
import com.ferchau.spain.weather.domain.model.PrecipitationResult;
import com.ferchau.spain.weather.domain.model.WeatherPredictionResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class RetrieveWeatherPrediction implements IRetrieveWeatherPrediction {

    private IRetrieveWeatherPredictionRepository retrieveWeatherPredictionRepository;
    protected static int NUMBER_DAYS_TO_ADD = 1;
    protected static String TEMPERATURE_UNIT_FAHRENHEIT = "G_FAH";

    @Override
    public WeatherPredictionResult execute(String cityId, String temperatureUnit) {
        var weatherPredictions = retrieveWeatherPredictionRepository.execute(cityId);

        var tomorrow = LocalDate.now().plusDays(NUMBER_DAYS_TO_ADD);
        var nextDayWeatherPrediction = weatherPredictions.stream()
                .filter(weatherPrediction -> tomorrow.equals(weatherPrediction.getDate().toLocalDate()))
                .findFirst()
                .orElse(weatherPredictions.getFirst());

        return new WeatherPredictionResult()
                .setAverageTemperature(getAverageTemperatureByUnit(nextDayWeatherPrediction.getTemperature(), temperatureUnit))
                .setTemperatureUnit(temperatureUnit)
                .setPrecipitationProbability(getPrecipitationResults(nextDayWeatherPrediction.getPrecipitationProbability()));
    }

    private Double getAverageTemperatureByUnit(List<Measure> measures, String temperatureUnit) {
        var result = Arrays.stream(measures.stream().map(Measure::getValue).mapToInt(Long::intValue).toArray())
                .average().orElse(Double.NaN);
        if (TEMPERATURE_UNIT_FAHRENHEIT.equals(temperatureUnit)) {
            result = (result * 1.8) + 32;
        }
        return result;
    }

    private List<PrecipitationResult> getPrecipitationResults(List<Measure> precipitationProbabilities) {
        return precipitationProbabilities.stream()
                .map(this::getPrecipitationResult)
                .toList();
    }

    private PrecipitationResult getPrecipitationResult(Measure measure) {
        var period = measure.getPeriod().substring(0, 2) + "-" + measure.getPeriod().substring(2);
        return new PrecipitationResult().setProbability(measure.getValue().intValue())
                .setPeriod(period);
    }
}
