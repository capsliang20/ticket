package com.line.ticket.common.entity.request;

import com.line.ticket.common.entity.generic.AbstractRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DemoRequest extends AbstractRequest {
    private static final long serialVersionUID = 8456871734169123553L;

    private Integer id;

    private String name;
}
