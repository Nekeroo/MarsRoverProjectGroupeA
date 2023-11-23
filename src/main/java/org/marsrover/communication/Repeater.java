package org.marsrover.communication;

import org.marsrover.config.Configuration;
import org.marsrover.console.LoggerConsole;
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

    private final ServerSocket serverSocketForClient;

    public Repeater() throws IOException {
        serverSocketForClient = new ServerSocket(portEcoute);
        socketServer = new Socket(Configuration.HOST, portEnvoi);
        consoleServer = new SocketConsole(socketServer, new LoggerConsole());
        consoleServer.log("Connexion Server OK");
    }

    public void startRepeater() throws IOException {
        // Crée un serveur pour accepter les connexions du client

        while (!serverSocketForClient.isClosed()) {

            acceptClientSocket();
            // Créez un thread pour gérer la communication du client au serveur
            Thread clientToServerThread = new Thread(() -> {
                try {
                    relayDataForServer(clientSocket.getInputStream());
                } catch (IOException e) {
                    consoleClient.log("1");
                }
            });

            // Créez un thread pour gérer la communication du serveur au client
            Thread serverToClientThread = new Thread(() -> {
                try {
                    relayDataForClient(socketServer.getInputStream());
                } catch (IOException e) {
                    consoleClient.log("2");
                }
            });

            // Lancez les deux threads
            serverToClientThread.start();
            clientToServerThread.start();
        }
    }

    private void acceptClientSocket() throws IOException {
        clientSocket = serverSocketForClient.accept();
        consoleClient = new SocketConsole(clientSocket, new LoggerConsole());
        consoleClient.log("Connexion Client OK");
    }

    /**
     * Méthode de transfert des données au Client
     * @param in
     * @throws IOException
     */
    private void relayDataForClient(InputStream in) throws IOException {
        // Utilisez BufferedReader pour lire des chaînes de caractères
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = reader.readLine()) != null) {
            consoleClient.writeLine(line);
            consoleClient.log("Client : " +line);
        }
    }

    /**
     * Méthode de transfert des données au Server
     * @param in
     * @throws IOException
     */
    private void relayDataForServer(InputStream in) throws IOException {
        // Utilisez BufferedReader pour lire des chaînes de caractères
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = reader.readLine()) != null) {
            consoleServer.writeLine(line);
            consoleServer.log("Server : "+line);
        }
    }

    public void shutDown() throws IOException {
        consoleClient = null;
        consoleServer = null;
        clientSocket.close();
        socketServer.close();
        serverSocketForClient.close();
        socketServer = null;
        clientSocket = null;
    }

    public static void main(String[] args) throws IOException {
        Repeater repeater = new Repeater();
        repeater.startRepeater();
    }

}
