package com.line.ticket.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

@SpringBootApplication
@MapperScan("com.line.ticket.service.mapper")
@ImportResource(value = "classpath:dubbo/ticket-service-provider.xml")
public class LineTicketServiceApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(LineTicketServiceApplication.class, args).registerShutdownHook();
        System.in.read();
    }

}
