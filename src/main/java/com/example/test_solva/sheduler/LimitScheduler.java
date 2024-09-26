package com.example.test_solva.sheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class LimitScheduler {
    /**
     * Запланированная задача, которая выполняется каждый месяц в 5:00 утра.
     */
    @Scheduled(cron = "0 0 5 1 * ?", zone = "GMT+5")
    void schedulerForResetLimit() {
        log.info("Cron started");
        log.info("Cron ended");
    }
}
