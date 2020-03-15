package com.line.ticket.common.entity.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Ticket implements Serializable {
    private static final long serialVersionUID = 5852401852849131584L;

    private Integer id;
    /**
     * 票名
     */
    private String name;
    /**
     * 票价
     */
    private Integer price;
    /**
     * 剩余票数
     */
    private Integer remains;
    /**
     * 票总数
     */
    private Integer sum;
    /**
     * 开售时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saleStartTime;
    /**
     * 演出时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime showStartTime;
}
