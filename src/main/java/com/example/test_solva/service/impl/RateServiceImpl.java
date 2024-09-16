package com.example.test_solva.service.impl;

import com.example.test_solva.model.dto.Rate;
import com.example.test_solva.model.entity.RateEntity;
import com.example.test_solva.model.enums.CurrencyEnum;
import com.example.test_solva.repository.RateRepository;
import com.example.test_solva.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.example.test_solva.model.constant.ExceptionTextConstants.DATA_NOT_FOUND_IN_DB;
import static com.example.test_solva.model.constant.ExceptionTextConstants.INCORRECT_RATE_TITLE_ERROR;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
    private final RateRepository repository;

    @Override
    public List<RateEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public RateEntity findById(LocalDate id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save (RateEntity rateEntity) {
        repository.save(rateEntity);
    }

    @Override
    public List<RateEntity> findTop(int count) {
        Pageable pageable = PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "createdDate"));
        return repository.findAll(pageable).getContent();
    }

    @Override
    @Transactional
    public void saveAll (List<RateEntity> list) {
        repository.saveAll(list);
    }

    @Override
    @Transactional
    public RateEntity updateByDate(LocalDate date, Rate rate) {
        RateEntity rateInDb = repository.findById(date)
                .orElseThrow( () -> new IllegalArgumentException (DATA_NOT_FOUND_IN_DB));
        if(Arrays.stream(CurrencyEnum.values())
                .map(currency -> currency.label)
                .noneMatch(label -> label.equals(rate.getTitle()))){
            throw new IllegalArgumentException (INCORRECT_RATE_TITLE_ERROR);
        }
        rateInDb.getRate().put(rate.getTitle(), rate);
        return repository.save(rateInDb);
    }

    @Override
    @Transactional
    public void deleteByDate(LocalDate date) {
        RateEntity rate = repository.findById(date)
                .orElseThrow(() -> new IllegalArgumentException (DATA_NOT_FOUND_IN_DB));
        repository.delete(rate);
    }

    @Override
    public List<RateEntity> findAllByCreatedDateIn(List<LocalDate> dates) {
        return repository.findAllByCreatedDateIn(dates);
    }

}
