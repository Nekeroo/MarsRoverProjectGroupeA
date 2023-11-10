package org.marsrover.communication;

import org.marsrover.config.Configuration;
import org.marsrover.console.Logger;
import org.marsrover.console.SocketConsole;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Repeater {

    private final static int portEcoute = Configuration.PORT_CLIENT;
    private final static int portEnvoi = Configuration.PORT_SERVER;

    private Socket clientSocket;

    private Socket socketServer;
    private SocketConsole consoleClient;
    private SocketConsole consoleServer;

    public void startRepeater() {
        try {
            // Crée un serveur pour accepter les connexions du client
            ServerSocket serverSocketForClient = new ServerSocket(portEcoute);

            while (true) {
                // Attendez la connexion du client

                if (clientSocket == null) {
                    clientSocket = serverSocketForClient.accept();
                    consoleClient = new SocketConsole(clientSocket, new Logger());
                    consoleClient.log("Connexion Client OK");
                }
                // Connexion au serveur
                if (socketServer == null ){
                    socketServer = new Socket(Configuration.HOST, portEnvoi);
                    consoleServer = new SocketConsole(socketServer, new Logger());
                    consoleClient.log("Connexion Server OK");
                }

                // Créez un thread pour gérer la communication du client au serveur
                Thread clientToServerThread = new Thread(() -> {
                    try {
                        relayData(consoleServer);
                    } catch (IOException e) {
                        consoleClient.log(e.getMessage());
                    }
                });

                // Créez un thread pour gérer la communication du serveur au client
                Thread serverToClientThread = new Thread(() -> {
                    try {
                        relayData(consoleClient);
                    } catch (IOException e) {
                        consoleClient.log(e.getMessage());
                    }
                });

                // Lancez les deux threads
                serverToClientThread.start();
                clientToServerThread.start();
            }
        } catch (IOException e) {
            consoleClient.log(e.getMessage());
        }
    }


    private void relayData(SocketConsole console) throws IOException {
        String line;
        while ((line = console.readLine()) != null) {
            console.writeLine(line);
            console.log(line);
        }
    }

    public static void main(String[] args) {
        Repeater repeater = new Repeater();
        repeater.startRepeater();
    }
}
