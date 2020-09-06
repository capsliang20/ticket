package com.line.ticket.common.entity.request;

import com.line.ticket.common.entity.generic.AbstractRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class DemoRequest extends AbstractRequest {
    private static final long serialVersionUID = 8456871734169123553L;

    private Integer id;

    private String name;

    private int intId;

    private int[] intArray;

    private Integer[] integerArray;

    private List<Integer> integers;

    private List<String> strings;

    private String[] stringArray;

    private Map<String, String> stringStringMap;

    private Map<Integer, String> integerStringMap;

    private Map<String, Integer> stringIntegerMap;
}
