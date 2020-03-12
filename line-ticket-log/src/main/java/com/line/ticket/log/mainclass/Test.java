package com.line.ticket.log.mainclass;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Test {
    public static void main(String[] args){
        LocalDate from=LocalDate.of(2020,1,22);
        LocalDate to =LocalDate.of(2020,5,1);
        System.out.println(ChronoUnit.DAYS.between(from,to));

    }
}
