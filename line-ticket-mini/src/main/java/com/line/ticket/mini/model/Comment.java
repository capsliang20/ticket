package com.line.ticket.mini.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = -7537099296262553033L;

    private String commentContent;

    private Integer replyCount;

    private Boolean status;
}
