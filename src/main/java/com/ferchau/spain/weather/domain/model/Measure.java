package com.ferchau.spain.weather.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Measure {

    private Long value;
    private String period;

}
