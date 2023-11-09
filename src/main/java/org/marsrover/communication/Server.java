package org.marsrover.communication;

import org.marsrover.rover.IRover;
import org.marsrover.rover.LocalRover;

import java.io.*;
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
    public IRover listenAndSendResponse(LocalRover rover) {

        Interpreter interpreter = new Interpreter();
        try {
            LocalRover roverResult = null;
            if (socketClient == null)
                socketClient = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            String command = in.readLine();
            System.out.println("Server read : " + command);
            if (interpreter.mapStringToCommand(command) != null) {
                roverResult = (LocalRover) interpreter.mapStringToCommand(command).execute(rover);
            }

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

            String x = String.valueOf(roverResult.getCurrentCoordinates().x());
            String y = String.valueOf(roverResult.getCurrentCoordinates().y());
            String direction = roverResult.getCurrentDirection().toString();

            String message = x + "," + y + "," + direction;
            out.write(message);
            out.newLine();
            out.flush();
            System.out.println("Server sent " + message);
            return roverResult;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
