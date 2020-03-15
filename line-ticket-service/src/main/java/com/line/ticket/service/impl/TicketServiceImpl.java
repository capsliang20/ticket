package com.line.ticket.service.impl;

import com.line.ticket.common.api.TicketService;
import com.line.ticket.common.entity.constant.TicketConstant;
import com.line.ticket.common.entity.generic.Pair;
import com.line.ticket.common.entity.generic.Result;
import com.line.ticket.common.entity.service.Ticket;
import com.line.ticket.common.entity.service.TicketOrder;
import com.line.ticket.service.mapper.TicketMapper;
import com.line.ticket.service.mapper.TicketOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
@Slf4j
public class TicketServiceImpl implements TicketService {
    @Resource
    private TicketMapper ticketMapper;

    @Resource
    private TicketOrderMapper recordMapper;

    @Override
    public Result<Ticket> getTicketDetail(Integer id) {
        Ticket result = ticketMapper.getTicketInfo(id);
        if (result == null) {
            return Result.fail("ticket " + id + " may not exist!");
        }
        return Result.success(result);
    }

    @Override
    public Result buyTicket(Integer ticketId, Integer userId) {
        if (ticketId == null || userId == null) {
            return Result.fail("Both ticketId and userId should not be null.");
        }
        Integer remains = ticketMapper.getTicketRemains(ticketId);
        if (remains == null || remains == 0) {
            return Result.fail("No more tickets.");
        }
        if (ticketMapper.updateTicketRemains(ticketId, remains, TicketConstant.NEGATIVE_ONE) == null) {
            return Result.fail("Buy ticket error, remains: " + remains);
        }
        TicketOrder record = new TicketOrder();
        record.setTicketId(ticketId);
        record.setUserId(userId);
        record.setProcessId(TicketConstant.PROCESS_STATUS_SCHEDULED_ID);
        record.setProcessStatus(TicketConstant.PROCESS_STATUS_SCHEDULED_VALUE);
        record.setOrderStatus(TicketConstant.ORDER_STATUS_NOT_FINISHED);
        recordMapper.addTicketOrder(record);
        return Result.success();
    }

    @Override
    public Result refundTicket(Integer orderId, Integer userId) {
        if (orderId == null || userId == null) {
            return Result.fail("Both orderId and userId should not be null.");
        }
        Pair<Integer, Integer> pair = recordMapper.getTicketPair(orderId);
        if (pair == null) {
            return Result.fail("orderId may not exist");
        }
        recordMapper.updateTicketOrder(orderId, TicketConstant.PROCESS_STATUS_REFUND_ID,
                TicketConstant.PROCESS_STATUS_REFUND_VALUE, TicketConstant.ORDER_STATUS_FINISHED);

        ticketMapper.updateTicketRemains(pair.getKey(), pair.getValue(), TicketConstant.ONE);
        return Result.success();
    }
}
