package org.marsrover.communication;

import org.marsrover.config.Configuration;
import org.marsrover.console.Logger;
import org.marsrover.rover.IRover;
import org.marsrover.rover.LocalRover;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Classe Server permettant de d'héberger un ServerSocket et de communiquer avec le Client
 */
public class Server implements IMessageServer {

    private final ServerSocket server;
    private final Logger logger;
    private SocketConsole console;
    private Socket socketClient;
    private final ExecutorService executorService;

    public Server(Logger logger) {
        try {
            this.server = new ServerSocket(Configuration.PORT);
            this.socketClient = null;
            this.executorService = Executors.newFixedThreadPool(5);
            this.logger = logger;
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

                String command = console.readline();
                console.log("Server read: " + command);

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
        if (socketClient == null) {
            socketClient = server.accept();
            this.console = new SocketConsole(socketClient, logger);
        }
    }

    private void sendResponseToClient(IRover roverResult) throws IOException {
        String x = String.valueOf(roverResult.getCurrentCoordinates().x());
        String y = String.valueOf(roverResult.getCurrentCoordinates().y());
        String direction = roverResult.getCurrentDirection().toString();

        String message = x + "," + y + "," + direction;

        console.writeLine(message);
        console.log("Server sent " + message);
    }

    private void sendErrorToClient() throws IOException {
        String message = "X";
        console.writeLine(message);
        console.log("Server sent " + message);
    }
}
