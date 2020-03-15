package com.line.ticket.common.api;

import com.line.ticket.common.entity.generic.Result;
import com.line.ticket.common.entity.service.Ticket;

public interface TicketService {
    Result<Ticket> getTicketDetail(Integer id);

    /**
     * 买票
     *
     * @param ticketId 票务id
     * @param userId   用户id
     * @return 购买申请是否成功发起
     */
    Result buyTicket(Integer ticketId, Integer userId);

    /**
     * 退票
     *
     * @param orderId 订单id
     * @param userId  用户id
     * @return 退票申请是否成功发起
     */
    Result refundTicket(Integer orderId, Integer userId);
}
