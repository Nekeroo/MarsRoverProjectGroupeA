package org.marsrover.rover;

import org.marsrover.communication.Communicator;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;
import org.marsrover.topologie.Position;

import java.io.IOException;

public class NetworkRover implements IRover {

    private Communicator communicator;

    private Position position;

    public NetworkRover(Position position) {
        this.communicator = new Communicator();
        this.position = position;
    }



    @Override
    public NetworkRover moveForward() {
        communicator.sendCommand("Z");


        Direction direction =
        Position position = new Position(new Coordinates(x, y), direction);
        return NetworkRover()
    }

    @Override
    public NetworkRover moveBack() {
        return communicator.decryptInfos(communicator.sendAnswer(this));
    }

    @Override
    public NetworkRover turnLeft() {
        return communicator.decryptInfos(communicator.sendAnswer(this));
    }

    @Override
    public NetworkRover turnRight() {
        String status = communicator.sendCommand("D");
        String[] roverInfos = status.split(",");
        int x = Integer.parseInt(roverInfos[0]);
        int y = Integer.parseInt(roverInfos[1]);
        Direction direction = Direction.getDirectionFromString(roverInfos[2]);

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
