package com.line.ticket.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Ticket implements Serializable {
    private static final long serialVersionUID = 5852401852849131584L;
    private Integer id;
    private String name;
    private Integer price;
    private Integer remains;
    private Integer sum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saleStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime showStartTime;
}
