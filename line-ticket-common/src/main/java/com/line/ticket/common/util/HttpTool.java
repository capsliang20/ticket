package com.line.ticket.common.util;

import com.line.ticket.common.entity.service.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpTool {
    private static final PoolingHttpClientConnectionManager clientManager = new PoolingHttpClientConnectionManager();

    public static String post(String uri, Map<String, String> paramMap, Object body) {
        log.info("uri:{}", uri);
        HttpClient httpClient = HttpClients.createMinimal(clientManager);
        try {
            HttpPost post = new HttpPost(buildURI(uri, paramMap));
            StringEntity entity = new StringEntity(JsonTool.toJSONString(body), StandardCharsets.UTF_8);
            entity.setContentType(ContentType.APPLICATION_JSON.toString());
            post.setEntity(entity);
            HttpEntity response = httpClient.execute(post).getEntity();
            return EntityUtils.toString(response, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.warn("HttpPost error. uri:{}", uri, e);
            return null;
        }
    }

    private static URI buildURI(String uri, Map<String, String> paramMap) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(uri);
        if (paramMap != null) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                uriBuilder.addParameter(entry.getKey(), entry.getValue());
            }
        }
        return uriBuilder.build();
    }

    public static String get(String uri, Map<String, String> paramMap) {
        log.info("uri:{}, paramMap:{}", uri, paramMap);
        HttpClient httpClient = HttpClients.createMinimal(clientManager);
        try {
            HttpGet get = new HttpGet(buildURI(uri, paramMap));
            HttpEntity response = httpClient.execute(get).getEntity();
            return EntityUtils.toString(response, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("HttpGet error. uri:{}", uri, e);
            return null;
        }
    }
//
//    public static void main(String[] args) {
//        Map<String, String> paramMap = new HashedMap<>();
//        paramMap.put("id", "11");
//        paramMap.put("name", "nam");
//        paramMap.put("intId", "22");
//        paramMap.put("intArray", JsonTool.toJSONString(List.of(1, 2, 3)));
//        paramMap.put("integerArray", JsonTool.toJSONString(List.of(4, 5, 6)));
//        paramMap.put("integers", JsonTool.toJSONString(List.of(7, 8, 9)));
//        paramMap.put("strings", JsonTool.toJSONString(List.of("aa", "bb", "cc")));
//        paramMap.put("stringArray", JsonTool.toJSONString(List.of("ee", "ff", "gg")));
//        paramMap.put("stringStringMap", JsonTool.toJSONString(Map.of("ae", "af", "be", "bf", "ce", "cf")));
//        paramMap.put("integerStringMap", JsonTool.toJSONString(Map.of(1, "aa", 2, "bb", 3, "cc")));
//        paramMap.put("stringIntegerMap", JsonTool.toJSONString(Map.of("aa", 2, "bb", 3, "cc", 4)));
//
//        System.out.println(get("http://localhost:8088/ticket/demo/demoGet", paramMap));
//        System.out.println(post("http://localhost:8088/ticket/demo/ticketPost", Map.of("id", "1", "account", "xx"), new Ticket()));
//
//    }
}
