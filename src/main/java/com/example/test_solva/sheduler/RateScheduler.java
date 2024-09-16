package com.example.test_solva.sheduler;

import com.example.test_solva.service.IntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class RateScheduler {
    private final IntegrationService integrationService;

    /**
     * Запланированная задача, которая выполняется ежедневно утром в 5:00.
     */
    @Scheduled(cron = "0 0 5 * * ?", zone = "GMT+5")
    void schedulerForSmsMonitoring() {
        log.info("Cron started");
        integrationService.getExchangeRates(null, null);
        log.info("Cron ended");
    }
}