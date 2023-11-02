package org.marsrover.controlMission;

import org.marsrover.abstractCommunications.ICommandSender;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MissionControl implements ICommandSender {

    private int port;

    public MissionControl(int port) {
        this.port = port;
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nextCommand;
    }

    public static void main(String[] args) {
        MissionControl missionControl = new MissionControl(8080);

        while (true) {
            String command = missionControl.send();
            System.out.println("Sent command: " + command);
        }
    }

}
