package com.line.ticket.log.mainclass;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "testttt")
public class MyApp {
    public static void main(String[] args) throws InterruptedException {
        Bar bar = new Bar();
        bar.print();
        log.trace("Entering application.");
        log.info("info application");
        log.debug("debug level");
        log.warn("warn");
        log.error("error !");
    }
}
