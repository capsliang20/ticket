package com.line.ticket.log.ju;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String id;
    private Integer location;
    private Integer priority;

    public static final Comparator<Item> ITEM_COMPARATOR = Comparator.comparingInt((Item o) -> o.location).thenComparingInt(o -> o.priority).thenComparing(o -> o.id);
}


