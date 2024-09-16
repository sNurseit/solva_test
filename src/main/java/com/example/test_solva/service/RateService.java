package com.example.test_solva.service;

import com.example.test_solva.model.dto.Rate;
import com.example.test_solva.model.entity.RateEntity;

import java.time.LocalDate;
import java.util.List;

public interface RateService {
    List<RateEntity> findAll();

    RateEntity findById(LocalDate id);

    void save(RateEntity rateEntity);

    List<RateEntity> findTop(int count);

    void saveAll(List<RateEntity> list);

    RateEntity updateByDate(LocalDate date, Rate rate);

    void deleteByDate(LocalDate date);

    List<RateEntity> findAllByCreatedDateIn (List<LocalDate> dates);
}
