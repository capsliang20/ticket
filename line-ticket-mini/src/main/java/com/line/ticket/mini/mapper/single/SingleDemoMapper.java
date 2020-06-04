package com.line.ticket.mini.mapper.single;

import com.line.ticket.mini.model.single.SingleDemo;
import org.apache.ibatis.annotations.Param;

public interface SingleDemoMapper {
    SingleDemo querySingleDemo(@Param("id") Integer id);
}
