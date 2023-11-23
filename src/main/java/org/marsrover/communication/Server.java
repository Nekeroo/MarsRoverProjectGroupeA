package org.marsrover.communication;

import org.marsrover.config.Configuration;
import org.marsrover.console.Logger;
import org.marsrover.console.SocketConsole;
import org.marsrover.rover.IRover;
import org.marsrover.rover.Rover;
import org.marsrover.rover.roverCommands.IRoverCommand;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Classe Server permettant d'héberger un ServerSocket et de communiquer avec le Client
 */
public class Server implements IMessageServer {

    private final ServerSocket server;
    private final Logger logger;
    private SocketConsole console;
    private Socket socketClient;
    private final ExecutorService executorService;

    public Server(Logger logger) {
        try {
            this.server = new ServerSocket(Configuration.PORT_SERVER);
            this.socketClient = null;
            this.executorService = Executors.newFixedThreadPool(5);
            this.logger = logger;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompletableFuture<IRover> listenAndSendResponse(Rover rover) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Rover tmpRover = rover;
                Rover roverResult;
                acceptClientSocketIfNull();

                String data = console.readline();
                console.log("Server read: " + data);

                Interpreter interpreter = new Interpreter();
                roverResult = null;

                if (data.length() > 1){
                    if (interpreter.isSequenceValid(List.of(data.split("")))){
                        List<IRoverCommand> commands = interpreter.mapStringToCommandList(data);
                        for (IRoverCommand command : commands){
                            console.log("Commande exécutée : " + interpreter.mapCommandToString(command));
                            roverResult = (Rover) command.execute(tmpRover);
                            tmpRover = roverResult;
                        }
                    }
                }
                else if (interpreter.isSequenceValid(List.of(data))) {
                    roverResult = (Rover) interpreter.mapStringToCommand(data).execute(tmpRover);
                }
                else {
                    console.log("Commande inconnue trouvée dans la séquence");
                }

                if (!rover.equals(roverResult))
                    sendErrorToClient();
                else
                    sendResponseToClient(roverResult);
                return roverResult == null ? rover : roverResult;
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