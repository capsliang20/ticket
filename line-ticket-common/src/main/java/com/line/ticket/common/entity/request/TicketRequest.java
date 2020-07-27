package com.line.ticket.common.entity.request;

import com.line.ticket.common.entity.generic.AbstractBodyRequest;
import com.line.ticket.common.entity.service.Ticket;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TicketRequest extends AbstractBodyRequest<Ticket> {

    private static final long serialVersionUID = 2024804467193569040L;

    private Integer id;

    private String account;

    private Ticket ticket;

    @Override
    public Ticket getBody() {
        return ticket;
    }

    @Override
    public void setBody(Ticket body) {
        this.ticket = body;
    }
}

