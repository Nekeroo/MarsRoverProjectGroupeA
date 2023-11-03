package org.marsrover.controlMission;

import org.marsrover.communication.SocketCommunicator;

import java.io.IOException;
import java.util.Scanner;

public class MissionControl {

    private SocketCommunicator socketCommunicator;

    public MissionControl() { this.socketCommunicator = new SocketCommunicator();}

    public static void main(String[] args) {
        MissionControl missionControl = new MissionControl();

        while (true) {
            Scanner console = new Scanner(System.in);
            String nextCommand = console.nextLine();
            String command = missionControl.socketCommunicator.sendCommand(nextCommand);
            System.out.println("Sent command: " + command);
        }
    }

}
