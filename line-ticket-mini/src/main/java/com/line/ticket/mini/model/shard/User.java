package com.line.ticket.mini.model.shard;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = -51197093662763346L;

    private Long id;

    private String userKey;

    private String userValue;

    private Integer areaCode;
}
