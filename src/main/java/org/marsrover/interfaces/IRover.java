package org.marsrover.interfaces;

import org.marsrover.Coordinates;
import org.marsrover.Direction;
import org.marsrover.Position;

public interface IRover {
    Position getCurrentPosition();
    Direction getCurrentDirection();
    Coordinates getCurrentCoordinates();

    IRover moveForward();
    IRover moveBack();
    IRover turnLeft();
    IRover turnRight();
}
