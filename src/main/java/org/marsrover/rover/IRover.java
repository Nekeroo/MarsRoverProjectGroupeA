package org.marsrover.rover;

import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;

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
