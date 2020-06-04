package com.line.ticket.mini.model.shard;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShardFullUser implements Serializable {

    private static final long serialVersionUID = 1031751018817134760L;

    private Long id;

    private String userKey;

    private String userValue;

    private String account;

    private String password;

    private Integer areaCode;

    private String areaName;
}
