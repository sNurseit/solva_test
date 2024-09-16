package com.example.test_solva.model.enums;

public enum ExpenseCategoryEnum {
    PRODUCT("PRODUCT"),
    SERVICE ("SERVICE");

    public final String label;

    ExpenseCategoryEnum(String label) {
        this.label = label;
    }
}
