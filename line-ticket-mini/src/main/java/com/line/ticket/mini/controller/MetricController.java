package com.line.ticket.mini.controller;

import com.line.ticket.mini.model.Result;
import io.micrometer.core.instrument.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class MetricController {
    @Autowired
    private MeterRegistry meterRegistry;

    @PostMapping("counter")
    public Result counter(@RequestParam("name") String name, @RequestParam("data") Double data,
                          @RequestBody Map<String, String> tagMap) {
        List<Tag> tags = new ArrayList<>(tagMap.entrySet().size() * 2);
        tagMap.forEach((k, v) -> tags.add(Tag.of(k, v)));
        Counter counter = meterRegistry.counter(name, tags);
        Map<String, Object> map = new HashMap<>(tagMap);
        map.put("before", counter.count());
        counter.increment(data);
        map.put("after", counter.count());
        map.put("name", name);
        return Result.success(map);
    }

    @PostMapping("timer")
    public Result timer(@RequestParam("name") String name, @RequestParam("data") Integer data,
                        @RequestBody Map<String, String> tagMap) {
        List<Tag> tags = new ArrayList<>(tagMap.entrySet().size() * 2);
        tagMap.forEach((k, v) -> tags.add(Tag.of(k, v)));
        Timer timer = meterRegistry.timer(name, tags);
        timer.record(Duration.ofSeconds(data));
        Map<String, Object> map = new HashMap<>(tagMap);
        map.put("name", name);
        map.put("timer.count", timer.count());
        map.put("timer.total", timer.totalTime(TimeUnit.SECONDS));
        return Result.success(map);
    }

    @PostMapping("summary")
    public Result summary(@RequestParam("name") String name, @RequestParam("data") Double data,
                          @RequestBody Map<String, String> tagMap) {
        List<Tag> tags = new ArrayList<>(tagMap.entrySet().size() * 2);
        tagMap.forEach((k, v) -> tags.add(Tag.of(k, v)));
        DistributionSummary summary = meterRegistry.summary(name, tags);
        summary.record(data);
        Map<String, Object> map = new HashMap<>(tagMap);
        map.put("name", name);
        map.put("summary.count", summary.count());
        return Result.success(map);
    }

}
