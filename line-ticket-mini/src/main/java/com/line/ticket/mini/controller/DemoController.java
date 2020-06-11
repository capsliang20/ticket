package com.line.ticket.mini.controller;

import com.line.ticket.mini.model.Result;
import com.line.ticket.mini.model.shard.Area;
import com.line.ticket.mini.model.shard.Record;
import com.line.ticket.mini.util.JSON;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @PostMapping(path= "/po")
    public Result poResult(@RequestBody Map<String, String> map) {
        System.out.println(JSON.toJSONString(map));
        Record record = new Record();
        record.setId(11l);
        record.setType(1);
        record.setContent("content");
        record.setDateNum(20200611);
        return Result.success(record);
    }

    @GetMapping(value = "/ge")
    public Result geResult(@Param("code") Integer code, @Param("name") String name) {
        System.out.println(code + " " + name);
        Area area = new Area();
        area.setCode(code);
        area.setName(name);
        return Result.success(area);
    }
}
