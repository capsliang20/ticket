package com.line.ticket.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ImportResource(value = "classpath:dubbo/ticket-service-consumer.xml")
public class LineTicketConsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(LineTicketConsoleApplication.class, args);
    }

}
