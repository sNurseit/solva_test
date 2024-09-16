package com.example.test_solva.model.entity;


import com.example.test_solva.model.enums.CurrencyEnum;
import com.example.test_solva.model.enums.ExpenseCategoryEnum;
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
@Table(name = "history_transaction_table")
public class TransactionHistoryEntity {

    @Id
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_from", nullable = false, columnDefinition = "INTEGER")
    @Digits(integer = 10, fraction = 0)
    private Integer accountFrom;

    @Column(name = "account_to", nullable = false, columnDefinition = "INTEGER")
    @Digits(integer = 10, fraction = 0)
    private Integer accountTo;

    @Column(name = "currency_shortname", nullable = false, columnDefinition = "VARCHAR(3)")
    @Enumerated(EnumType.STRING)
    private CurrencyEnum currencyShortname;

    @Column(name = "amount", nullable = false, columnDefinition = "double precision")
    @Digits(integer = 10, fraction = 2)
    private Double sum;

    @Column(name = "expense_category", nullable = false, columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private ExpenseCategoryEnum expenseCategory;
}
