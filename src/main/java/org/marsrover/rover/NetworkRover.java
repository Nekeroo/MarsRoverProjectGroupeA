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
        return communicator.decryptInfos(communicator.sendAnswer(this));
    }

    @Override
    public IRover moveBack() {
        return communicator.decryptInfos(communicator.sendAnswer(this))
    }

    @Override
    public IRover turnLeft() {
        return communicator.decryptInfos(communicator.sendAnswer(this));
    }

    @Override
    public IRover turnRight() {
        return communicator.decryptInfos(communicator.sendAnswer(this))
    }

    @Override
    public Direction getCurrentDirection() {
        return communicator.sendCommand("Direction");
    }

    @Override
    public Coordinates getCurrentCoordinates() {
        return communicator.sendCommand("Coordinates");
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
