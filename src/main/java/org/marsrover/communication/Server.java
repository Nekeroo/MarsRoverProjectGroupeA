package org.marsrover.communication;

import org.marsrover.rover.IRover;
import org.marsrover.rover.LocalRover;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements IMessageServer {

    private final ServerSocket server;

    private Socket socketClient;
    private final ExecutorService executorService;

    public Server() {
        try {
            this.server = new ServerSocket(Configuration.PORT);
            this.socketClient = null;
            this.executorService = Executors.newFixedThreadPool(5);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompletableFuture<IRover> listenAndSendResponse(LocalRover rover) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                LocalRover roverResult;
                acceptClientSocketIfNull();

                String command = readCommandFromClient();
                System.out.println("Server read: " + command);

                Interpreter interpreter = new Interpreter();
                roverResult = null;
                if (interpreter.mapStringToCommand(command) != null) {
                    roverResult = (LocalRover) interpreter.mapStringToCommand(command).execute(rover);
                }

                if (!rover.equals(roverResult))
                    sendErrorToClient();
                else
                    sendResponseToClient(roverResult);
                return roverResult;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, executorService);
    }

    private void acceptClientSocketIfNull() throws IOException {
        if (socketClient == null)
            socketClient = server.accept();
    }

    private String readCommandFromClient() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        return in.readLine();
    }

    private void sendResponseToClient(IRover roverResult) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

        String x = String.valueOf(roverResult.getCurrentCoordinates().x());
        String y = String.valueOf(roverResult.getCurrentCoordinates().y());
        String direction = roverResult.getCurrentDirection().toString();

        String message = x + "," + y + "," + direction;
        out.write(message);
        out.newLine();
        out.flush();

        System.out.println("Server sent " + message);
    }

    private void sendErrorToClient() throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

        String message = "X";

        out.write(message);
        out.newLine();
        out.flush();

        System.out.println("Server sent " + message);
    }
}
