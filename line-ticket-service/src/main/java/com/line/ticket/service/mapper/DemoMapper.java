package com.line.ticket.service.mapper;

import com.line.ticket.common.entity.service.Demo;
import org.apache.ibatis.annotations.Param;

public interface DemoMapper {
    Demo selectDemo(@Param("id") Integer id);

    Integer insertDemo(Demo demo);

    Integer deleteDemo(@Param("id") Integer id);

    Integer updateDemo(Demo demo);
}
