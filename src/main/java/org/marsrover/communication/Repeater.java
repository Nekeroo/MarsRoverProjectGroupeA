package org.marsrover.communication;

import org.marsrover.config.Configuration;
import org.marsrover.console.Logger;
import org.marsrover.console.SocketConsole;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

public class Repeater {

    private final static int portEcoute = Configuration.PORT_CLIENT;
    private final static int portEnvoi = Configuration.PORT_SERVER;

    private Socket clientSocket;
    private SocketConsole console;

    public void startRepeater() {
        try {
            // Crée un serveur pour accepter les connexions du client
            ServerSocket serverSocket = new ServerSocket(portEcoute);

            while (true) {
                // Attendez la connexion du client

                if (clientSocket == null) {
                    clientSocket = serverSocket.accept();
                    console = new SocketConsole(clientSocket, new Logger());
                }
                // Connexion au serveur
                Socket server = new Socket(Configuration.HOST, portEnvoi);

                // Créez un thread pour gérer la communication du client au serveur
                Thread clientToServerThread = new Thread(() -> {
                    try {
                        relayData(clientSocket.getInputStream(), server.getOutputStream());
                    } catch (IOException e) {
                        console.log(e.getMessage());
                    }
                });

                // Créez un thread pour gérer la communication du serveur au client
                Thread serverToClientThread = new Thread(() -> {
                    try {
                        relayData(server.getInputStream(), clientSocket.getOutputStream());
                    } catch (IOException e) {
                        console.log(e.getMessage());
                    }
                });

                // Lancez les deux threads
                clientToServerThread.start();
                serverToClientThread.start();
            }
        } catch (IOException e) {
            console.log(e.getMessage());
        }
    }

    private void relayData(InputStream in, OutputStream out) throws IOException {
        // Utilisez BufferedReader pour lire des chaînes de caractères
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        // Utilisez BufferedWriter pour écrire des chaînes de caractères
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

        String line;
        while ((line = reader.readLine()) != null) {
            console.writeLine(line);
        }
    }

    public static void main(String[] args) {
        Repeater repeater = new Repeater();
        repeater.startRepeater();
    }
}
