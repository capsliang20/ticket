package com.line.ticket.mini.http;

import com.line.ticket.mini.util.HttpTool;

import java.io.IOException;
import java.util.Map;

public class HttpClientMain {
    public static void main(String[] args) throws IOException {
        String get="http://localhost:8080/mini/demo/ge?code=1&name=xx搜索x";
        System.out.println(HttpTool.get(get));

        String post="http://localhost:8080/mini/demo/po";
        Map<String,Object> map=Map.of("kekey", "vavalu搜索是是e","nynvy", "zaza 是是设定看了","xmxmm", 111);
        System.out.println(HttpTool.post(post,map));
    }
}
