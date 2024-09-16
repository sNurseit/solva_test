package com.example.test_solva.service;

import com.example.test_solva.model.entity.RateEntity;

import java.time.LocalDate;
import java.util.List;

public interface IntegrationService {
    List<RateEntity> getExchangeRates(LocalDate startDate, LocalDate endDate);
}
