package com.line.ticket.mini.controller;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.line.ticket.mini.model.Comment;
import com.line.ticket.mini.model.PbComment;
import com.line.ticket.mini.model.Result;
import com.line.ticket.mini.model.shard.Area;
import com.line.ticket.mini.model.shard.Record;
import com.line.ticket.mini.util.GeneratorTool;
import com.line.ticket.mini.util.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("demo")
@Slf4j
public class DemoController {

    @Autowired
    private KafkaTemplate<String, String> template;

    @PostMapping(path = "po")
    public Result poResult(@RequestBody Map<String, String> map) {
        System.out.println(JSON.toJSONString(map));
        Record record = new Record();
        record.setId(11L);
        record.setType(1);
        record.setContent("content");
        record.setDateNum(20200611);
        return Result.success(record);
    }

    @GetMapping(value = "ge")
    public Result geResult(@Param("code") Integer code, @Param("name") String name) {
        System.out.println(code + " " + name);
        Area area = new Area();
        area.setCode(code);
        area.setName(name);
        return Result.success(area);
    }

    @GetMapping("startSirius")
    public Result startSirius() throws InterruptedException {
        log.info("now sirius system start...");
        List<String> msgIds = List.of(GeneratorTool.str(5), GeneratorTool.str(5), GeneratorTool.str(5), GeneratorTool.str(5), GeneratorTool.str(5));
        Map<String, List<String>> sendHistory = new HashMap<>();
        msgIds.forEach(msgId -> sendHistory.put(msgId, new ArrayList<>()));
        log.info("msgIds:{}", msgIds);
        Random random = GeneratorTool.random();
        for (int i = 0; i < 100; i++) {
            StringBuilder sb = new StringBuilder(GeneratorTool.num(8) + ",");
            for (int j = 0; j < random.nextInt(20); j++) {
                sb.append(GeneratorTool.num(8)).append(",");
            }
            String msgId = msgIds.get(random.nextInt(msgIds.size()));
            String imeiStr = sb.deleteCharAt(sb.length() - 1).toString();
            log.info("now send message msgId:{}, imeiList:{}", msgId, imeiStr);
            template.send("sirius", msgId, imeiStr);
            sendHistory.get(msgId).add(imeiStr);
            log.info("----------now sleep 1000ms ----------");
            Thread.sleep(1000);
        }
        return Result.success(sendHistory);
    }

    @PostMapping("proto")
    public byte[] comment(@RequestBody Comment comment) throws InvalidProtocolBufferException {
        PbComment.Comment.Builder builder = PbComment.Comment.newBuilder();
        JsonFormat.parser().merge(JSON.toJSONString(comment), builder);
        return builder.build().toByteArray();
    }
}
