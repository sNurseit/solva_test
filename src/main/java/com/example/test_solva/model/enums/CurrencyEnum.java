package com.example.test_solva.model.enums;

public enum CurrencyEnum {
    EUR("EUR"),
    USD ("USD"),
    RUB ("RUB");

    public final String label;

    CurrencyEnum(String label) {
        this.label = label;
    }
}
