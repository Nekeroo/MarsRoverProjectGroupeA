package org.marsrover.rover;

import org.marsrover.communication.Communicator;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;

import java.io.IOException;

public class NetworkRover implements IRover {

    private Communicator communicator;

    public NetworkRover() {
        this.communicator = new Communicator();
    }

    @Override
    public IRover moveForward() {
        return communicator.sendCommand("Z");
    }

    @Override
    public IRover moveBack() {
        return communicator.sendCommand("S");
    }

    @Override
    public IRover turnLeft() {
        return communicator.sendCommand("Q");
    }

    @Override
    public IRover turnRight() {
        return communicator.sendCommand("D");
    }

    @Override
    public Direction getCurrentDirection() {
        return null;
    }

    @Override
    public Coordinates getCurrentCoordinates() {
        return null;
    }

    public static void main(String[] args) {
        NetworkRover networkRover = new NetworkRover();
        try {
            networkRover.communicator.startListening(networkRover);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
