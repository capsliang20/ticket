package com.line.ticket.log.mainclass;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Bar {
    private String bar;
    public void print(){
        log.trace("bar trace level");
        log.info("bar info level");
        log.debug("bar debug level");
        log.warn("bar warn level");
        log.error("bar error level");
    }
}
