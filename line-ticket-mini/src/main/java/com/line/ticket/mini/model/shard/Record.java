package com.line.ticket.mini.model.shard;

import lombok.Data;

import java.io.Serializable;

@Data
public class Record implements Serializable {
    private static final long serialVersionUID = 603456940471680539L;

    private Long id;

    private String content;

    private Integer dateNum;

    private Integer type;
}
