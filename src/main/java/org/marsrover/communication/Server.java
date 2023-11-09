package org.marsrover.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements IMessageServer {

    private ServerSocket server;

    private Socket socketClient;

    public Server() {
        try {
            this.server = new ServerSocket(Configuration.PORT);
            this.socketClient = null;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String listenAndSendResponse() {
        try {
            if (socketClient == null)
                socketClient = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            String command = in.readLine();
            System.out.println(command);
            return command;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
