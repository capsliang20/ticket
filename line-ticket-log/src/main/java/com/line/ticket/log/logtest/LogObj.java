package com.line.ticket.log.logtest;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LogObj {

    public void markLog() {
        log.info("hello log4j2");
    }
}
