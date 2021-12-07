package com.bka.ssi.controller.verification.company.aop.configuration.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ThreadPoolTaskSchedulerConfig {

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(
        @Value("${scheduler.poolSize}") int poolSize) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler
            = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(poolSize);
        threadPoolTaskScheduler.setThreadNamePrefix(
            "ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }
}
