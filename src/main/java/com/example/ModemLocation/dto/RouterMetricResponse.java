package com.example.ModemLocation.dto;

public record RouterMetricResponse(
        Double latitude,
        Double longitude,
        String status
) {
}
