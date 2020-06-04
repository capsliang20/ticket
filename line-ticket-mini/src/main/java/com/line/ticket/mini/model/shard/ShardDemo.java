package com.line.ticket.mini.model.shard;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShardDemo implements Serializable {

    private static final long serialVersionUID = -4364492056591010809L;

    private Long id;

    private String name;

    private Integer areaCode;

    private Integer dateNum;
}
