package com.line.ticket.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class HttpTool {
    private static final PoolingHttpClientConnectionManager clientManager = new PoolingHttpClientConnectionManager();

    public static String post(String url, Map<String, Object> params) {
        log.info("url:{}", url);
        HttpClient httpClient = HttpClients.createMinimal(clientManager);
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(JsonTool.toJSONString(params), StandardCharsets.UTF_8);
        entity.setContentType(ContentType.APPLICATION_JSON.toString());
        post.setEntity(entity);
        try {
            HttpEntity response = httpClient.execute(post).getEntity();
            return EntityUtils.toString(response, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.warn("HttpPost error. url:{}", url, e);
            return null;
        }
    }

    public static String get(String url) {
        log.info("url:{}", url);
        HttpClient httpClient = HttpClients.createMinimal(clientManager);
        HttpGet get = new HttpGet(url);
        try {
            HttpEntity response = httpClient.execute(get).getEntity();
            return EntityUtils.toString(response, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.warn("HttpGet error. url:{}", url, e);
            return null;
        }
    }
}
