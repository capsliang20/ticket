package com.line.ticket.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
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
}
