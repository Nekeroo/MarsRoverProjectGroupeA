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
                        relayDataForServer(clientSocket.getInputStream());
                    } catch (IOException e) {
                        consoleClient.log(e.getMessage());
                    }
                });

                // Créez un thread pour gérer la communication du serveur au client
                Thread serverToClientThread = new Thread(() -> {
                    try {
                        relayDataForClient(socketServer.getInputStream());
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

    private void relayDataForClient(InputStream in) throws IOException {
        // Utilisez BufferedReader pour lire des chaînes de caractères
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = reader.readLine()) != null) {
            consoleClient.writeLine(line);
            consoleClient.log("Client : " +line);
        }
    }

    private void relayDataForServer(InputStream in) throws IOException {
        // Utilisez BufferedReader pour lire des chaînes de caractères
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = reader.readLine()) != null) {
            consoleServer.writeLine(line);
            consoleServer.log("Server : "+line);
        }
    }

    public static void main(String[] args) {
        Repeater repeater = new Repeater();
        repeater.startRepeater();
    }

}
