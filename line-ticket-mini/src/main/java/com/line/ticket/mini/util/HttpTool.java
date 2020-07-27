package com.line.ticket.mini.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.http.HttpHeaderValue;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpTool {
    private static final PoolingHttpClientConnectionManager clientManager = new PoolingHttpClientConnectionManager();

    public static String post(String url, Map<String, Object> params) throws IOException {
        HttpClient httpClient = HttpClients.createMinimal(clientManager);
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(JSON.toJSONString(params), StandardCharsets.UTF_8);
        entity.setContentType(MediaType.APPLICATION_JSON_VALUE);
        post.setEntity(entity);
        HttpEntity response = httpClient.execute(post).getEntity();
        return EntityUtils.toString(response, StandardCharsets.UTF_8);
    }

    public static String get(String url) throws IOException {
        HttpClient httpClient = HttpClients.createMinimal(clientManager);
        HttpGet get = new HttpGet(url);
        HttpEntity response = httpClient.execute(get).getEntity();
        return EntityUtils.toString(response, StandardCharsets.UTF_8);
    }
}
