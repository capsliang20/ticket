package com.line.ticket.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LineTicketConsoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(LineTicketConsoleApplication.class, args);
    }

}
