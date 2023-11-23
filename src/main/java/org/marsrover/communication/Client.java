package org.marsrover.communication;

import org.marsrover.config.Configuration;
import org.marsrover.console.LoggerConsole;
import org.marsrover.console.SocketConsole;
import org.marsrover.rover.IRover;
import org.marsrover.rover.NetworkRover;

import java.io.*;
import java.net.Socket;

/**
 * Classe Client permettant de créer un Socket Client et de communiquer avec le Server
 */
public class Client implements IMessageClient{
    private final SocketConsole console;
    public Client() {
        try {
            Socket client = new Socket(Configuration.HOST, Configuration.PORT_CLIENT);
            this.console = new SocketConsole(client, new LoggerConsole());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public IRover SendAndWaitForResponse(String message) {
        String roverResult;
        NetworkRover networkRover = null;
        Interpreter interpreter = new Interpreter();

        console.writeLine(message);

        console.log("Message sent : " + message);

        String response = console.readline();

        if (!response.isEmpty() && !response.equals("X")) {
            System.out.println(response);
            roverResult = response;
            networkRover = interpreter.mapRoverFromString(roverResult);
            console.log("New Rover : " + networkRover.toString());
        }
        else {
            console.log("Commande inconnue trouvée dans la séquence");
        }

        return networkRover;
    }
}
