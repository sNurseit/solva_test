package com.example.test_solva.repository;

import com.example.test_solva.model.entity.LimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitRepository extends JpaRepository<LimitEntity, Long> {
}
