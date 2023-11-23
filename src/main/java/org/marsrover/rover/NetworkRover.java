package org.marsrover.rover;

import org.marsrover.topology.Coordinates;
import org.marsrover.topology.Direction;
import org.marsrover.topology.Position;

/**
 * NetworkRover est une classe utilisée côté Client afin de transformer les informations reçus d'un Rover sous la forma
 * de String par le réseau en objet NetworkRover
 */
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
