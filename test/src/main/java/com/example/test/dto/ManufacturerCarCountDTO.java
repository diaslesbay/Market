package com.example.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ManufacturerCarCountDTO {
    private String manufacturer;
    private Long carCount;

    public ManufacturerCarCountDTO(String manufacturer, Long carCount) {
        this.manufacturer = manufacturer;
        this.carCount = carCount;
    }


}
