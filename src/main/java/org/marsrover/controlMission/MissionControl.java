package org.marsrover.controlMission;

import org.marsrover.communication.Client;

import java.util.Scanner;

public class MissionControl {

    public static void main(String[] args) {
        Client client = new Client();
        Scanner console = new Scanner(System.in);

        while (true){
            String message = console.nextLine();
            if (message != "")
                client.SendAndWaitForResponse(message);
        }
    }

}
