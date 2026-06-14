package com.example.ModemLocation.persistence.repository;

import com.example.ModemLocation.persistence.model.RouterMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouterMetricRepository extends JpaRepository<RouterMetric, Long> {
}
