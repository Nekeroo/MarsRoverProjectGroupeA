package org.marsrover.records;

public record Direction(String name, Vector vector)
{
    private static final Direction North = new Direction("N", new Vector(0, 1));
    private static final Direction South = new Direction("S", new Vector(0, -1));
    private static final Direction East = new Direction("E", new Vector(1, 0));
    private static final Direction West = new Direction("W", new Vector(-1, 0));

    public Direction getNextDirectionFromClockwise()
    {
        if (this.equals(North))
            return East;
        if (this.equals(East))
            return South;
        if (this.equals(South))
            return West;
        return North;
    }

    public Direction getNextDirectionCounterClockwise()
    {
        return getNextDirectionFromClockwise().getNextDirectionFromClockwise().getNextDirectionFromClockwise();
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
