package com.example.ModemLocation.dto;

public record RouterMetricRequest(
        Integer mcc,
        Integer mnc,
        Integer lac,
        Integer cellId,
        String radio,
        String routerId,
        Integer connectedClients
){
}
