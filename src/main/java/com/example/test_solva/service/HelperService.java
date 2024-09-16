package com.example.test_solva.service;

import jakarta.xml.bind.JAXBException;

import java.time.LocalDate;
import java.util.List;

public interface HelperService {

    /**
     * Преобразует XML-строку в объект указанного типа
     *
     * @param xmlString XML в виде строки
     * @param responseType тип класса, к которому должен быть преобразован XML строка
     * @return  экземпляр указанного типа с данными
     * @throws JAXBException выбарсываемое исключение при ошибке переобразования
     */
    <T> T unmarshalData(String xmlString, Class<T> responseType) throws JAXBException;

    String formatLocalDate(LocalDate date);

    List<LocalDate> getLocalDateList(LocalDate startDate, LocalDate endDate);
}
