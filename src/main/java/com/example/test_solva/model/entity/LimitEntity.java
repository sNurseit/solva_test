package com.example.test_solva.model.entity;

import com.example.test_solva.model.enums.CurrencyEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "limit")
public class LimitEntity {

    @Id
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "limit_sum", nullable = false, columnDefinition = "DOUBLE")
    @Digits(integer = 10, fraction = 2)
    private Double LimitSum;

    @Column(name = "limit_datetime", nullable = false, columnDefinition = "DATE")
    private LocalDateTime limitDatetime;

    @Column(name = "limit_currency_shortname", nullable = false, columnDefinition = "VARCHAR(3)")
    @Enumerated(EnumType.STRING)
    private CurrencyEnum limitCurrencyShortname;

}
