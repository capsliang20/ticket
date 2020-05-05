package com.line.ticket.console;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.netty.MockServer;
import org.mockserver.proxyconfiguration.ProxyConfiguration;

import java.io.IOException;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class MockServerTest {

    private static ClientAndServer server;

    public static void main(String[] args) throws IOException {

        ProxyConfiguration configuration = ProxyConfiguration.proxyConfiguration(ProxyConfiguration.Type.HTTP, "www.qwwaq.com");
        MockServer mockServer = new MockServer(configuration, 80);
        server = new ClientAndServer(80);
        server.when(request().withMethod("GET").withPath("/test"))
                .respond(response().withBody("OKOKOKxxx"));
    }

//    @BeforeAll
//    public static void start() {
//        System.out.println("server start");
//        server = new ClientAndServer("www.qwwaq.com", 1080);
//        server.when(request().withMethod("GET").withPath("/test"))
//                .respond(response().withBody("OKOKOK"));
//    }
//
//
//
//    @AfterAll
//    public static void stop() {
//        System.out.println("server stop");
//        server.stop();
//    }
}
