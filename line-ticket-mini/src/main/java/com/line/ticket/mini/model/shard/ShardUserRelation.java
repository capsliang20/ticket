package com.line.ticket.mini.model.shard;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShardUserRelation implements Serializable {

    private static final long serialVersionUID = 2369066011659156904L;

    private Long id;

    private Long userId;

    private String userAccount;

    private String userPassword;

    private Integer areaCode;
}
