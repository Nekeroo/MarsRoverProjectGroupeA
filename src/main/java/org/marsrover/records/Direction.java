package org.marsrover.records;

import org.marsrover.collections.DirectionCollection;

public record Direction(String name, Vector vector)
{
    private static final DirectionCollection directionCollection = new DirectionCollection();

    public Direction getNextDirectionFromClockwise() {
        return directionCollection.getNextDirectionFromClockwise(this);
    }

    public Direction getNextDirectionCounterClockwise() {
        return directionCollection.getNextDirectionCounterClockwise(this);
    }

    public Integer vectorX() {
        return vector.vectorX();
    }

    public Integer vectorY() {
        return vector.vectorY();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
