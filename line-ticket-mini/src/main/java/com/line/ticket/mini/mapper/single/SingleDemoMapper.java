package com.line.ticket.mini.mapper.single;

import com.line.ticket.mini.model.single.Demo;
import org.apache.ibatis.annotations.Param;

public interface SingleDemoMapper {
    Demo querySingleDemo(@Param("id") Integer id);
}
