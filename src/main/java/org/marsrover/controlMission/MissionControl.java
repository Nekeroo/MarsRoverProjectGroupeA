package org.marsrover.controlMission;

import org.marsrover.communication.Communicator;
import org.marsrover.communication.SocketCommunicator;
import org.marsrover.rover.IRover;
import org.marsrover.rover.NetworkRover;

import java.io.IOException;
import java.util.Scanner;

public class MissionControl {

    public static void main(String[] args) {
        MissionControl missionControl = new MissionControl();
        NetworkRover networkRover = new NetworkRover();
        Communicator communicator = new Communicator();

        while (true) {
            Scanner console = new Scanner(System.in);
            String nextCommand = console.nextLine();
            communicator.mapStringToCommand(nextCommand).execute(networkRover);
            System.out.println("Sent command: " + nextCommand);
        }
    }

}
