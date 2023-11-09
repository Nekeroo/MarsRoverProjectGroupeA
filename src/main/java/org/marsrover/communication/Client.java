package org.marsrover.communication;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client implements IMessageClient{

    private Socket client;

    public Client() {
        try {
            this.client = new Socket(Configuration.HOST, Configuration.PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String SendAndWaitForResponse(String message) {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            out.write(message);
            out.newLine();
            out.flush();

            return "Message sent : " + message;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
