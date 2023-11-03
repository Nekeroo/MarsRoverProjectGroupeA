package org.marsrover.rover;

import org.marsrover.planet.Planet;
import org.marsrover.topologie.Coordinates;
import org.marsrover.topologie.Direction;

public interface IRover {

    IRover moveForward();
    IRover moveBack();
    IRover turnLeft();
    IRover turnRight();

    Direction getCurrentDirection();

    Coordinates getCurrentCoordinates();
}
