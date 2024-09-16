package com.example.test_solva.service.impl;

import com.example.test_solva.model.dto.Rate;
import com.example.test_solva.model.dto.Rates;
import com.example.test_solva.model.entity.RateEntity;
import com.example.test_solva.model.enums.CurrencyEnum;
import com.example.test_solva.service.HelperService;
import com.example.test_solva.service.IntegrationService;
import com.example.test_solva.service.RateService;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.test_solva.model.constant.ExceptionTextConstants.OUT_OF_MIN_VALUE;
import static com.example.test_solva.model.constant.IntegrationConstants.F_DATE;
import static com.example.test_solva.model.constant.IntegrationConstants.MIN_DATE;

@Service
@Slf4j
@RequiredArgsConstructor
public class IntegrationServiceImpl implements IntegrationService {

    private final RestTemplate restTemplate;
    private final RateService rateService;
    private final HelperService helperService;

    @Value("${integration.url}")
    private String url;

    @Override
    public List<RateEntity> getExchangeRates(LocalDate startDate, LocalDate endDate) {
        if (startDate.isBefore(MIN_DATE) || endDate.isBefore(MIN_DATE)) {
            throw new IllegalArgumentException(OUT_OF_MIN_VALUE);
        }

        LocalDate targetStartDate = startDate != null ? startDate : LocalDate.now();
        LocalDate targetEndDate = endDate != null ? endDate : LocalDate.now().plusDays(1);

        List<LocalDate> listOfDates = helperService.getLocalDateList(targetStartDate, targetEndDate);
        List<RateEntity> ratesInDb = rateService.findAllByCreatedDateIn(listOfDates);
        List<LocalDate> datesForRequest = getDatesForRequest(ratesInDb, listOfDates);

        datesForRequest.forEach(
                date -> {
                    String formattedDate = helperService.formatLocalDate(date);
                    String response = fetchRatesFromApi(formattedDate);
                    try {
                        ratesInDb.add(parseAndSaveRates(response, date));
                    } catch (JAXBException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        return ratesInDb;
    }

    /**
     * Создает URL-адрес запроса API, используя указанную дату, и отправляет GET запрос для получения курсов.
     *
     * @param  formattedDate дата, на которую запрашиваются курсы валют, в формате строки ("dd.MM.yyyy").
     * @return  ответ в формате JSON в виде строки из API, содержащей обменные курсы на указанную дату.
     */
    private String fetchRatesFromApi(String formattedDate) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(F_DATE, formattedDate);
        return restTemplate.getForObject(uriBuilder.toUriString(), String.class);
    }

    private List<LocalDate> getDatesForRequest(List<RateEntity> list, List<LocalDate> dates) {
        return dates.stream()
                .filter(date -> list.stream().noneMatch(rateEntity -> rateEntity.getCreatedDate().equals(date)))
                .collect(Collectors.toList());
    }

    private RateEntity parseAndSaveRates(String response, LocalDate date) throws JAXBException {

        Rates rates = helperService.unmarshalData(response, Rates.class);
        Map<String, Rate> selectedRates = filterRelevantRates(rates.getRates());

        RateEntity rateEntity = new RateEntity();
        rateEntity.setRate(selectedRates);
        rateEntity.setCreatedDate(date);

        rateService.save(rateEntity);

        return rateEntity;

    }

    /**
     * Фильтрует предоставленный список курсов валют, чтобы извлечь только нужные данные ("USD", "EUR", "RUB").
     * @param rateList Список курсов валют, которые нужно отфильтровать.
     * @return Map, отфильтрованные курсы валют ("USD", "EUR", "RUB").
     */
    private Map<String, Rate> filterRelevantRates(List<Rate> rateList) {
        return rateList.stream()
                .filter(rate -> Arrays.stream(CurrencyEnum.values())
                        .map(currency -> currency.label)
                        .anyMatch(label -> label.equals(rate.getTitle())))
                .collect(Collectors.toMap(Rate::getTitle, rate -> rate));
    }

}
