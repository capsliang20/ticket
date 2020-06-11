package com.line.ticket.mini.model.shard;

import lombok.Data;

import java.io.Serializable;

@Data
public class Area implements Serializable {

    private static final long serialVersionUID = 2632113788656144517L;

    private Integer code;

    private String name;
}
