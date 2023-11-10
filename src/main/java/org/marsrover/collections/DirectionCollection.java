package org.marsrover.collections;

import org.marsrover.records.Direction;
import org.marsrover.records.Vector;

import java.util.List;
import java.util.stream.Stream;

public class DirectionCollection {
    private static final List<Direction> allDirections = Stream.of(
            new Direction("N", new Vector(0, 1)),
            new Direction("E", new Vector(1, 0)),
            new Direction("S", new Vector(0, -1)),
            new Direction("W", new Vector(-1, 0))
    ).toList();

    private static final int DIRECTIONS_COUNT = allDirections.size();

    public Direction getNextDirectionFromClockwise(Direction currentDirection) {
        int nextIndex = (allDirections.indexOf(currentDirection) + 1) % DIRECTIONS_COUNT;
        return allDirections.get(nextIndex);
    }

    public Direction getNextDirectionCounterClockwise(Direction currentDirection) {
        int previousIndex = (allDirections.indexOf(currentDirection) - 1 + DIRECTIONS_COUNT) % DIRECTIONS_COUNT;
        return allDirections.get(previousIndex);
    }
}
