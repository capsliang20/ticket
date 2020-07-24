package com.line.ticket.common.entity.request;

import com.line.ticket.common.entity.Demo;
import com.line.ticket.common.entity.generic.AbstractBodyRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DemoListRequest extends AbstractBodyRequest<List<Demo>> {
    private static final long serialVersionUID = 3233105112449306110L;

    private Integer id;

    private String key;

}
