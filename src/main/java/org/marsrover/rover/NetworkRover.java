package org.marsrover.rover;

import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;
import org.marsrover.topologie.Position;

public class NetworkRover implements IRover {

    private final Position position;

    public NetworkRover(Position position) {
        this.position = position;
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

    @Override
    public String toString() {
        return "x :" + getCurrentCoordinates().x() + "/ y : " + getCurrentCoordinates().y() + "/ direction : " + getCurrentDirection();
    }
}
