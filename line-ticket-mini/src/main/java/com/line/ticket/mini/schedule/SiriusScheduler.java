package com.line.ticket.mini.schedule;

import com.line.ticket.mini.kafka.SiriusKafkaListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SiriusScheduler {
    @Autowired
    private SiriusKafkaListener siriusKafkaListener;

    @Scheduled(cron = "0/5 * * * * ?")
    public void siriusListenerScan() {
        log.info("now scheduler scan the listener.");
        siriusKafkaListener.scan();
    }
}
