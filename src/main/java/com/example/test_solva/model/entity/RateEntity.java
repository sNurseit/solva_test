package com.example.test_solva.model.entity;

import com.example.test_solva.model.dto.Rate;
import com.example.test_solva.model.mapper.MapToStringConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@Entity
@Table(name = "rate_history")
@NoArgsConstructor
public class RateEntity {

    @Id
    @Column(name = "created_date", nullable = false, columnDefinition = "DATE", unique = true)
    private LocalDate createdDate;

    @Column(name = "rate", nullable = false, columnDefinition = "TEXT")
    @Convert(converter = MapToStringConverter.class)
    private Map<String, Rate> rate;
}
