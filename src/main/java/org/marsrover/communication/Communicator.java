package org.marsrover.communication;

import org.marsrover.rover.IRover;
import org.marsrover.rover.LocalRover;
import org.marsrover.rover.commands.*;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;

public class Communicator implements ICommandSender, ICommandListener {

    /**
     * Classe permettant de réaliser des communications par Socket
     * Ainsi que d'interpréter le message reçu pour le transformer en commande
     * voir même transformer le message en Rover et inversement
     */
    private static final int port = 8080;

    public Communicator() { }

    public int getPort() {
        return port;
    }


    @Override
    public String startListening(IRover rover) throws IOException {
        String command = "";
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Rover is listening on port " + this.getPort());

            while (true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                command = in.readLine();
                clientSocket.close();
                return command;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String sendAnswer(IRover rover) {
        String roverInfo = "";
        try {
            Socket socket = new Socket("localhost", port);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String positionX = String.valueOf(rover.getCurrentCoordinates().x());
            String positionY = String.valueOf(rover.getCurrentCoordinates().y());
            String direction = rover.getCurrentDirection().toString();

            roverInfo = positionX + "," + positionY + "," + direction;

            out.write(roverInfo);
            out.newLine(); // Ajoute une nouvelle ligne pour indiquer la fin de la commande
            out.flush(); // Assurez-vous que les données sont envoyées
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return roverInfo;
    }

    @Override
    public String sendCommand(String data) {
        try {
            Socket socket = new Socket("localhost", port);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            out.write(data);
            out.newLine();
            out.flush();

            socket.close();

            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return "Erreur d'envoi de la commande : " + e.getMessage();
        }

    }


}