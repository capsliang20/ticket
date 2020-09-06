package com.line.ticket.common.api;

import com.line.ticket.common.entity.service.Demo;

public interface DemoService {
    boolean addDemo(Demo demo);

    boolean deleteDemo(Integer id);

    Demo selectDemo(Integer id);

    boolean updateDemo(Demo demo);
}
