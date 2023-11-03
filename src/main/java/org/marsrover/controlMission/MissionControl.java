package org.marsrover.controlMission;

import org.marsrover.communication.SocketCommunicator;

public class MissionControl {

    private SocketCommunicator socketCommunicator;

    public MissionControl() { this.socketCommunicator = new SocketCommunicator();}

    public static void main(String[] args) {
        MissionControl missionControl = new MissionControl();

        while (true) {
            String command = missionControl.socketCommunicator.send();
            System.out.println("Sent command: " + command);
        }
    }

}
