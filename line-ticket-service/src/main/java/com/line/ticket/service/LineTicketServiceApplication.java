package com.line.ticket.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

@SpringBootApplication
@ImportResource(value = "classpath:application-context.xml")
@EnableCaching
public class LineTicketServiceApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(LineTicketServiceApplication.class, args).registerShutdownHook();
        System.in.read();
    }

}
