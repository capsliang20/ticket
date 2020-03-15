package com.line.ticket.service.mapper;

import com.line.ticket.common.entity.generic.Pair;
import com.line.ticket.common.entity.service.TicketOrder;
import org.apache.ibatis.annotations.Param;

public interface TicketOrderMapper {
    /**
     * 通过订单id查询该订单对应票的id和剩余票数
     * @param orderId 订单id
     * @return key: 票id；  value: 该票剩余票数
     */
    Pair<Integer,Integer> getTicketPair(@Param("orderId") Integer orderId);

    /**
     * 添加订单信息
     * @param order 订单信息
     * @return 添加的行数
     */
    Integer addTicketOrder(TicketOrder order);

    /**
     * 更新订单状态
     * @param orderId 订单id
     * @param processId 当前流程状态id
     * @param processStatus 当前流程状态名
     * @param orderStatus 订单状态, True代表已完成, False代表未完成
     * @return 更新行数
     */
    Integer updateTicketOrder(@Param("orderId") Integer orderId, @Param("processId") Integer processId,
                         @Param("processStatus") String processStatus, @Param("orderStatus") Boolean orderStatus);
}
