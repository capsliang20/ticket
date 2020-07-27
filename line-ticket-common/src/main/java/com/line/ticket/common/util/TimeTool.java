package com.line.ticket.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class TimeTool {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static String dateTimeFormat(TemporalAccessor temporal) {
        return DATE_TIME_FORMATTER.format(temporal);
    }

    public static String dateTimeNow() {
        return DATE_TIME_FORMATTER.format(LocalDateTime.now());
    }

    public static String dateNow() {
        return DATE_FORMATTER.format(LocalDate.now());
    }

    public static String dateFormat(TemporalAccessor temporal) {
        return DATE_FORMATTER.format(temporal);
    }
}
