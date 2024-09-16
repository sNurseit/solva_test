package com.example.test_solva.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class HelperServiceImplTest {

    @InjectMocks
    HelperServiceImpl helperServiceImpl;
    @Test
    void testFormatLocalDate() {
        String result = helperServiceImpl.formatLocalDate(LocalDate.of(2024, Month.AUGUST, 22));
        Assertions.assertEquals("22.08.2024", result);
    }

    @Test
    void testFormatLocalDate2() {
        String result = helperServiceImpl.formatLocalDate(LocalDate.of(2024, Month.AUGUST, 22));
        Assertions.assertEquals("22.08.2024", result);
    }

    @Test
    void testGetLocalDateList() {
        List<LocalDate> result = helperServiceImpl.getLocalDateList(LocalDate.of(2024, Month.AUGUST, 22), LocalDate.of(2024, Month.AUGUST, 22));
        Assertions.assertEquals(List.of(LocalDate.of(2024, Month.AUGUST, 22)), result);
    }
}
