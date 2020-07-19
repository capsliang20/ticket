package com.line.ticket.log.mock;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.MockServerRule;

import java.io.IOException;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


@Slf4j
public class JUnit4MockServerTest2 {
    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this);

    private MockServerClient mockServer = null;

    @Test
    public void testSomething() throws IOException {
        mockServer.when(
                request("/junit4/test2/case1")
                        .withMethod("GET")
        )
                .respond(
                        response().withBody("junit4_test2_case1_response")
                );
        mockServer.when(request("/junit4/test2/case2").withMethod("GET"))
                .respond(response().withBody("junit4_test2_case2_response"));

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(new HttpGet("http://localhost:" + mockServer.remoteAddress().getPort() + "/junit4/test1/case1"));
        log.info("JUnit4 test 2, port = {}, result:{}", mockServer.remoteAddress().getPort(), new String(response.getEntity().getContent().readAllBytes()));
        response = client.execute(new HttpGet("http://localhost:" + mockServer.remoteAddress().getPort() + "/junit4/test1/case2"));
        log.info("JUnit4 test 2, port = {}, result:{}", mockServer.remoteAddress().getPort(), new String(response.getEntity().getContent().readAllBytes()));
    }
}
