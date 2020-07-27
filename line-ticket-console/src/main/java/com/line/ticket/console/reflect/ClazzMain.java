package com.line.ticket.console.reflect;

import java.util.ArrayList;
import java.util.List;

public class ClazzMain {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        System.out.println(List.class.isInstance(null));
        System.out.println(List.class.isInstance(list));

    }
}
