package com.line.ticket.log.mock;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.jupiter.MockServerExtension;

import java.io.IOException;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;


@ExtendWith(MockServerExtension.class)
@Slf4j
public class JUnit5MockServerTest2 {

    private final ClientAndServer mockServer;

    public JUnit5MockServerTest2(ClientAndServer mockServer) {
        this.mockServer = mockServer;
    }


    @Test
    public void testSomething() throws IOException {
        mockServer.when(
                request("/junit5/test2/case1")
                        .withMethod("GET")
        )
                .respond(
                        response().withBody("junit5_test2_case2_response")
                );
        mockServer.when(request("/junit5/test2/case2").withMethod("GET"))
                .respond(response().withBody("junit5_test2_case2_response"));

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(new HttpGet("http://localhost:" + mockServer.remoteAddress().getPort() + "/junit5/test2/case1"));
        log.info("JUnit5 test 2, port = {}, result:{}", mockServer.remoteAddress().getPort(), new String(response.getEntity().getContent().readAllBytes()));
        response = client.execute(new HttpGet("http://localhost:" + mockServer.remoteAddress().getPort() + "/junit5/test2/case2"));
        log.info("JUnit5 test 2,port = {}, result:{}", mockServer.remoteAddress().getPort(), new String(response.getEntity().getContent().readAllBytes()));
    }
}
