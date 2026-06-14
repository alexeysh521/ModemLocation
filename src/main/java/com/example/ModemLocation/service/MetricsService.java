package com.example.ModemLocation.service;

import com.example.ModemLocation.dto.RouterMetricRequest;
import com.example.ModemLocation.dto.RouterMetricResponse;
import com.example.ModemLocation.persistence.model.RouterMetric;
import com.example.ModemLocation.persistence.repository.RouterMetricRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class MetricsService {

    @Value("${opencell.token}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final RouterMetricRepository routerMetricRepository;

    public MetricsService(RestTemplate restTemplate, RouterMetricRepository routerMetricRepository) {
        this.restTemplate = restTemplate;
        this.routerMetricRepository = routerMetricRepository;
    }

    public RouterMetricResponse processAndSave(RouterMetricRequest dto) {
        String url = String.format(
                "https://opencellid.org/cell/get?key=%s&mcc=%d&mnc=%d&lac=%d&cellid=%d&radio=%s&format=json",
                apiKey, dto.mcc(), dto.mnc(), dto.lac(), dto.cellId(), dto.radio().toLowerCase()
        );

        String statusResponse = "";

        RouterMetric metric = new RouterMetric();
        metric.setMcc(dto.mcc());
        metric.setMnc(dto.mnc());
        metric.setLac(dto.lac());
        metric.setCellId(dto.cellId());
        metric.setConnectedClients(dto.connectedClients());
        metric.setRouterId(dto.routerId());
        metric.setRadio(dto.radio());

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            if (response != null && response.containsKey("error")) {
                statusResponse = "Вышка не найдена в базе OpenCellID";
                metric.setLatitude(0.0);
                metric.setLongitude(0.0);
            } else if (response != null && response.containsKey("lat")) {
                statusResponse = "Координаты получены";
                metric.setLatitude((Double) response.get("lat"));
                metric.setLongitude((Double) response.get("lon"));
            }
        } catch (Exception e) {
            metric.setLatitude(0.0);
            metric.setLongitude(0.0);
            statusResponse = "Ошибка на этапе запроса к OpenCellID";
        }
        routerMetricRepository.save(metric);
        return new RouterMetricResponse(metric.getLatitude(), metric.getLongitude(), statusResponse);
    }

    public List<RouterMetric> viewAll() {
        return routerMetricRepository.findAll();
    }
}
