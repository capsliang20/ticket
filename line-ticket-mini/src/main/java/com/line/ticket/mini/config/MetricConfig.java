package com.line.ticket.mini.config;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class MetricConfig {

    @Autowired
    private MeterRegistry meterRegistry;

    @Bean
    public Gauge gauge() {
        return Gauge.builder("test.gauge", () -> ThreadLocalRandom.current().nextDouble()).register(meterRegistry);
    }
}
