package com.line.ticket.mini.elastic;

import lombok.Data;

import java.io.Serializable;

@Data
public class Record implements Serializable {
    private static final long serialVersionUID = 4148483676988900519L;

    private String strategy;

    private String buuid;

    private String mediaIds;

    private String dayno;
}
