package com.line.ticket.common.entity.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketOrder implements Serializable {
    private static final long serialVersionUID = 6889761658269846134L;

    private Integer id;
    /**
     * 票id
     */
    private Integer ticketId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 订单记录创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 订单记录完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishedTime;
    /**
     * 订单所处流程id
     */
    private Integer processId;
    /**
     * 订单所处流程名
     */
    private String processStatus;
    /**
     * 当前订单状态，False代表未完成，True代表已完成
     */
    private Boolean orderStatus;
}
