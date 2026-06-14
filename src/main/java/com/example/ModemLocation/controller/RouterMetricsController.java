package com.example.ModemLocation.controller;

import com.example.ModemLocation.dto.RouterMetricRequest;
import com.example.ModemLocation.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/metrics")
public class RouterMetricsController {

    @Autowired private MetricsService metricsService;

    @PostMapping
    public ResponseEntity<?> receiveMetrics(@RequestBody RouterMetricRequest dto) {
        return ResponseEntity.ok(metricsService.processAndSave(dto));
    }

    @GetMapping("/view/all")
    public ResponseEntity<?> viewAllMetrics(){
        return ResponseEntity.ok(metricsService.viewAll());
    }
}
