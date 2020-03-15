package com.line.ticket.service.mapper;

import com.line.ticket.common.entity.service.Ticket;
import org.apache.ibatis.annotations.Param;

public interface TicketMapper {
    /**
     * 获取票务信息
     * @param ticketId 票id
     * @return 票务信息
     */
    Ticket getTicketInfo(@Param("ticketId") Integer ticketId);

    /**
     * 更新余票
     * @param ticketId 票务id
     * @param remains 余票数
     * @param count 操作, 1 或 -1
     * @return 影响的行数
     */
    Integer updateTicketRemains(@Param("ticketId") Integer ticketId, @Param("remains") Integer remains, @Param("count") Integer count);

    /**
     * 获取余票信息
     * @param ticketId 票id
     * @return 余票数
     */
    Integer getTicketRemains(@Param("ticketId") Integer ticketId);
}
