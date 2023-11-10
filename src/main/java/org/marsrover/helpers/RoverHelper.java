package org.marsrover.helpers;

import org.marsrover.records.Coordinates;
import org.marsrover.records.Direction;

public class RoverHelper {
    public static Direction rotateDirection(Direction direction, boolean clockwise) {
        return clockwise ? direction.getNextDirectionFromClockwise() : direction.getNextDirectionCounterClockwise();
    }

    public static Coordinates calculateNewCoordinates(Coordinates currentCoordinates, Direction currentDirection, boolean forward) {
        return forward
                ? currentCoordinates.addCoordinates(currentCoordinates, currentDirection)
                : currentCoordinates.subtractCoordinates(currentCoordinates, currentDirection);
    }
}
