package org.marsrover.rover;

import org.marsrover.communication.Interpreter;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;
import org.marsrover.topologie.Position;

public class NetworkRover implements IRover {

//    private Communicator communicator;

    private Interpreter interpreter;

    private Position position;

    public NetworkRover(Position position) {
//        this.communicator = communicator;
        this.position = position;
        this.interpreter = new Interpreter();
    }

    @Override
    public NetworkRover moveForward() {
       return this;
    }

    @Override
    public NetworkRover moveBack() {
        return this;
    }

    @Override
    public NetworkRover turnLeft() {
        return this;
    }

    @Override
    public NetworkRover turnRight() {
        return this;
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
