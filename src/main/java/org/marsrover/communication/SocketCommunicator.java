package org.marsrover.communication;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketCommunicator implements ICommandSender, ICommandListener{

    private final int port;

    public SocketCommunicator() {
        this.port = 8080;
    }

    @Override
    public void startListening() throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Rover is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String data = in.readLine();
                System.out.println(data);
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String send() {
        Scanner console = new Scanner(System.in);
        String nextCommand = console.nextLine();
        try {
            Socket socket = new Socket("localhost", port);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write(nextCommand);
            out.newLine(); // Ajoute une nouvelle ligne pour indiquer la fin de la commande
            out.flush(); // Assurez-vous que les données sont envoyées
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return nextCommand;
    }

}
