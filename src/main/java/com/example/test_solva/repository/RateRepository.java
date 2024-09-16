package com.example.test_solva.repository;

import com.example.test_solva.model.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<RateEntity, LocalDate> {

    List<RateEntity> findAllByCreatedDateIn (List<LocalDate> dates);

}