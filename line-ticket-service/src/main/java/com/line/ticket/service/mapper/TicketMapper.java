package com.line.ticket.service.mapper;

import com.line.ticket.common.entity.Ticket;
import org.apache.ibatis.annotations.Param;

public interface TicketMapper {
    Ticket getTicketInfo(@Param("id") Integer id);
}
