package com.line.ticket.service.impl;

import com.line.ticket.common.api.DemoService;
import com.line.ticket.common.entity.Demo;
import com.line.ticket.service.mapper.DemoMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class DemoServiceImpl implements DemoService {

    @Resource
    DemoMapper demoMapper;

    @Override
    public boolean addDemo(Demo demo) {
        return demoMapper.insertDemo(demo) == 1;
    }

    @Override
    public boolean deleteDemo(Integer id) {
        return demoMapper.deleteDemo(id) == 1;
    }

    @Override
    @Cacheable(cacheNames = {"demo"}, key = "#id")
    public Demo selectDemo(Integer id) {
        return demoMapper.selectDemo(id);
    }

    @Override
    public boolean updateDemo(Demo demo) {
        return demoMapper.updateDemo(demo) == 1;
    }
}
