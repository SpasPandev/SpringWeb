package com.example.shedules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CronSchedulerDemo {

    private final static Logger LOGGER = LoggerFactory.getLogger(CronSchedulerDemo.class);

    //  Executing on every 10 seconds
    @Scheduled(cron = "*/10 * * * * *")
    public void showTimeWithCron() {

        LOGGER.info("Hello, from cron scheduler at {}", LocalDateTime.now());
    }
}
