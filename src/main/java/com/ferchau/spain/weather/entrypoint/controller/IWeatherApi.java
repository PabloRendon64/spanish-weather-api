package com.ferchau.spain.weather.entrypoint.controller;

import com.ferchau.spain.weather.domain.model.WeatherPredictionResult;
import com.ferchau.spain.weather.entrypoint.controller.contracts.CitiesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface IWeatherApi {

    @Operation(summary = "Search cities operation",
            description = "Search cities operation using given query name param")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CitiesResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "missing required query param", content = @Content)
    })
    ResponseEntity<CitiesResponse> searchCitiesOperation(
            @Parameter(description = "query name city", required = true) String queryName);

    @Operation(summary = "get weather prediction operation",
            description = "get weather prediction using city id and temperature unit")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = WeatherPredictionResult.class)) }),
            @ApiResponse(responseCode = "400", description = "missing required query param", content = @Content)
    })
    ResponseEntity<WeatherPredictionResult> getWeatherPredictionOperation(
            @Parameter(description = "city id query param", required = true) String cityId,
            @Parameter(description = "temperature unit query param") String temperatureUnit);

}
