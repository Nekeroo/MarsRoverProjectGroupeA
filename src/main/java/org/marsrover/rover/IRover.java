package org.marsrover.rover;

import org.marsrover.topology.Coordinates;
import org.marsrover.topology.Direction;

/**
 * Interface IRover est implémentée dans Rover et NetworkRover.
 */
public interface IRover {

    IRover moveForward();
    IRover moveBack();
    IRover turnLeft();
    IRover turnRight();

    Direction getCurrentDirection();

    Coordinates getCurrentCoordinates();
}
