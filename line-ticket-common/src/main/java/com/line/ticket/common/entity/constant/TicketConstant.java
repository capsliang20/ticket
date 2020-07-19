package com.line.ticket.common.entity.constant;

public class TicketConstant {
    public static final int ONE = 1;
    public static final int NEGATIVE_ONE = -1;
    /**
     * 订单所处流程状态---已预定
     */
    public static final int PROCESS_STATUS_SCHEDULED_ID = 1;
    public static final String PROCESS_STATUS_SCHEDULED_VALUE = "已预定";

    /**
     * 订单所处流程状态---退款中
     */
    public static final int PROCESS_STATUS_REFUND_ID = 8;
    public static final String PROCESS_STATUS_REFUND_VALUE = "已退款";

    public static final Boolean ORDER_STATUS_NOT_FINISHED=Boolean.FALSE;
    public static final Boolean ORDER_STATUS_FINISHED=Boolean.TRUE;

}