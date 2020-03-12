package com.line.ticket.log.mainclass;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class DictClient {
    public static void main(String[] args) {
        String host = "localhost";
        try {
            Socket socket = new Socket(host, 20220);
            System.out.println("There is a server on port " + 20220 + " of " + host);
            InputStreamReader reader = new InputStreamReader(socket.getInputStream());
            System.out.println(reader.read());
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println(e);
        } catch (IOException e) {
        }
    }

}
