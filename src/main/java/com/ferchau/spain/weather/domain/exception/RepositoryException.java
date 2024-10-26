package com.ferchau.spain.weather.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RepositoryException extends RuntimeException {

    private String message;

}