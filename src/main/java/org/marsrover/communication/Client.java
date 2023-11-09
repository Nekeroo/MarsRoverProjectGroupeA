package org.marsrover.communication;

import org.marsrover.rover.IRover;
import org.marsrover.rover.NetworkRover;

import java.io.*;
import java.net.Socket;

public class Client implements IMessageClient{

    private final Socket client;

    public Client() {
        try {
            this.client = new Socket(Configuration.HOST, Configuration.PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public IRover SendAndWaitForResponse(String message) {
        try {
            String roverResult;
            NetworkRover networkRover = null;
            Interpreter interpreter = new Interpreter();
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            out.write(message);
            out.newLine();
            out.flush();

            System.out.println("Message sent : " + message);

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String response = in.readLine();

            if (response != null && !response.isEmpty() && !response.equals("X")) {
                roverResult = response;
                networkRover = interpreter.mapRoverFromString(roverResult);
                System.out.println("New Rover : " + networkRover.toString());
            }

            return networkRover;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
