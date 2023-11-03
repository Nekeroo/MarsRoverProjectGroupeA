package org.marsrover.rover;

import org.marsrover.communication.Communicator;
import org.marsrover.communication.Interpreter;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;
import org.marsrover.topologie.Position;

public class NetworkRover implements IRover {

    private Communicator communicator;

    private Interpreter interpreter;

    private Position position;

    public NetworkRover(Position position, Communicator communicator) {
        this.communicator = communicator;
        this.position = position;
        this.interpreter = new Interpreter();
    }

    @Override
    public NetworkRover moveForward() {
        String status = communicator.sendCommand("Z");
        NetworkRover rover = interpreter.mapRoverFromString(status, communicator);
        return rover;
    }

    @Override
    public NetworkRover moveBack() {
        String status = communicator.sendCommand("S");
        NetworkRover rover = interpreter.mapRoverFromString(status, communicator);
        return rover;
    }

    @Override
    public NetworkRover turnLeft() {
        String status = communicator.sendCommand("Q");
        NetworkRover rover = interpreter.mapRoverFromString(status, communicator);
        return rover;
    }

    @Override
    public NetworkRover turnRight() {
        String status = communicator.sendCommand("D");
        NetworkRover rover = interpreter.mapRoverFromString(status, communicator);
        return rover;
    }

    @Override
    public Direction getCurrentDirection() {
        return position.direction();
    }

    @Override
    public Coordinates getCurrentCoordinates() {
        return position.coordinates();
    }

}
