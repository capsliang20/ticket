package com.line.ticket.service.mybatis;

import org.apache.commons.lang3.ArrayUtils;

public class Main {
    public static void main(String[] args) {
        String[] strings = null;
        System.out.println(ArrayUtils.isEmpty(strings));
        String[] strings1=new String[0];
        System.out.println(ArrayUtils.isEmpty(strings1));


    }
}
