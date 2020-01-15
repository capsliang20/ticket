package com.line.ticket.common.api;

import com.line.ticket.common.entity.Demo;

public interface DemoService {
    boolean addDemo(Demo demo);

    boolean deleteDemo(int id);

    Demo selectDemo(int id);

    boolean updateDemo(Demo demo);
}
