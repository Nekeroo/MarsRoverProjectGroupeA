package org.marsrover.communication;

import org.marsrover.config.Configuration;
import org.marsrover.console.LoggerConsole;
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
    private final LoggerConsole loggerConsole;
    private SocketConsole console;
    private Socket socketClient;
    private final ExecutorService executorService;

    public Server(LoggerConsole loggerConsole) {
        try {
            this.server = new ServerSocket(Configuration.PORT_SERVER);
            this.socketClient = null;
            this.executorService = Executors.newFixedThreadPool(5);
            this.loggerConsole = loggerConsole;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompletableFuture<IRover> listenAndSendResponse(Rover rover) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                acceptClientSocketIfNull();

                String data = console.readline();
                console.log("Server read: " + data);
                return processInputData(rover, data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, executorService);
    }

    private Rover processInputData(Rover rover, String data) throws IOException {
        Rover tmpRover = rover;
        Interpreter interpreter = new Interpreter();
        Rover roverResult = null;

        if (data.length() > 1)
            roverResult = processCommandSequence(data, interpreter, roverResult, tmpRover);
        else
            roverResult = processSingleCommand(data, interpreter, roverResult, tmpRover);

        SendMessageToClient(rover, roverResult);
        return roverResult == null ? rover : roverResult;
    }

    private void SendMessageToClient(Rover rover, Rover roverResult) {
        if (!rover.equals(roverResult))
            sendErrorToClient();
        else
            sendResponseToClient(roverResult);
    }

    private Rover processCommandSequence(String data, Interpreter interpreter, Rover roverResult, Rover tmpRover) {
        List<String> commandSequence = List.of(data.split(""));
        if (interpreter.isSequenceValid(commandSequence)){
            List<IRoverCommand> commands = interpreter.mapStringToCommandList(data);
            for (IRoverCommand command : commands){
                console.log("Commande exécutée : " + interpreter.mapCommandToString(command));
                roverResult = (Rover) command.execute(tmpRover);
                tmpRover = roverResult;
            }
        }
        return roverResult;
    }

    private Rover processSingleCommand(String data, Interpreter interpreter, Rover roverResult, Rover tmpRover) {
        if (interpreter.isSequenceValid(List.of(data))) {
            roverResult = (Rover) interpreter.mapStringToCommand(data).execute(tmpRover);
        }
        else {
            console.log("Commande inconnue trouvée dans la séquence");
        }
        return roverResult;
    }

    private void acceptClientSocketIfNull() throws IOException {
        if (socketClient == null) {
            socketClient = server.accept();
            this.console = new SocketConsole(socketClient, loggerConsole);
        }
    }

    private void sendResponseToClient(IRover roverResult) {
        String x = String.valueOf(roverResult.getCurrentCoordinates().x());
        String y = String.valueOf(roverResult.getCurrentCoordinates().y());
        String direction = roverResult.getCurrentDirection().toString();

        String message = x + "," + y + "," + direction;

        console.writeLine(message);
        console.log("Server sent " + message);
    }

    private void sendErrorToClient() {
        String message = "X";
        console.writeLine(message);
        console.log("Server sent " + message);
    }

    public void tearDown() throws IOException {
        server.close();
    }
}