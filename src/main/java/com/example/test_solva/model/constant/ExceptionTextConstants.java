package com.example.test_solva.model.constant;

import java.time.LocalDate;

public class ExceptionTextConstants {
    public final static String INCORRECT_RATE_TITLE_ERROR = "The title should be: USD or RUB or EUR";
    public final static String DATA_NOT_FOUND_IN_DB = "Data not found";
    public final static String START_DATE_CANNOT_BE_GREATER_END_DATE_ERROR = "Start date cannot be a greater than end date";
    public final static String CONVERTING_JSON_TO_MAP_ERROR = "Error converting JSON to map";
    public final static String CONVERTING_MAP_TO_JSON_ERROR = "Error converting JSON to map";
    public final static String OUT_OF_MIN_VALUE = "startDate or endDate must not be before 2024-07-01";
}
