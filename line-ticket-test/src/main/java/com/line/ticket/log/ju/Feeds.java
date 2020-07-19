package com.line.ticket.log.ju;

import lombok.Data;

import java.util.List;

@Data
public class Feeds {
    List<Item> items;
    List<String> articles;
}
