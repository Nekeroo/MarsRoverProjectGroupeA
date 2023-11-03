package org.marsrover.communication;

import org.marsrover.rover.IRover;
import org.marsrover.rover.commands.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Communicator implements ICommandSender, ICommandListener {

    private static final int port = 8080;

    public Communicator() { }

    public int getPort() {
        return port;
    }


    @Override
    public void startListening(IRover rover) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket();
            System.out.println("Rover is listening on port " + this.getPort());

            while (true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String command = in.readLine();
                System.out.println(command);
                if (mapStringToCommand(command) != null) {
                    rover = mapStringToCommand(command).execute(rover);
                    sendAnswer(rover);
                }
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IRover sendAnswer(IRover rover) {
        try {
            Socket socket = new Socket("localhost", port);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String positionX = String.valueOf(rover.getCurrentCoordinates().x());
            String positionY = String.valueOf(rover.getCurrentCoordinates().y());
            String direction = rover.getCurrentDirection().toString();

            String roverInfo = positionX + "," + positionY + "," + direction;

            out.write(roverInfo);
            out.newLine(); // Ajoute une nouvelle ligne pour indiquer la fin de la commande
            out.flush(); // Assurez-vous que les données sont envoyées
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return rover;
    }

    @Override
    public String sendCommand(String data) {
        try {
            Socket socket = new Socket("localhost", this.getPort()); // Se connecter au serveur sur le port 8080 (assurez-vous que le serveur est en cours d'exécution sur "localhost")
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Envoyer la commande sous forme de chaîne de caractères
            out.write(data);
            out.newLine(); // Ajoute une nouvelle ligne pour indiquer la fin de la commande
            out.flush(); // Assurez-vous que les données sont envoyées

            // Fermer la socket
            socket.close();

            // Vous pouvez retourner un indicateur de succès ou toute autre information appropriée si nécessaire
            return "Commande envoyée : " + data + "\n";
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs en fonction de vos besoins
            return "Erreur d'envoi de la commande : " + e.getMessage();
        }
    }

    public IRoverCommand mapStringToCommand(String data) {
        switch (data) {
            case RoverCommandMoveForward.COMMAND:
                return new RoverCommandMoveForward();
            case RoverCommandMoveBack.COMMAND:
                return new RoverCommandMoveBack();
            case RoverCommandTurnLeft.COMMAND:
                return new RoverCommandTurnLeft();
            case RoverCommandTurnRight.COMMAND:
                return new RoverCommandTurnRight();
        }
        return null;
    }
}
