package org.marsrover.records;

public record Direction(String name, int xVector, int yVector) {

    public static final Direction North = new Direction("N", 0, 1);
    public static final Direction South = new Direction("S", 0, -1);
    public static final Direction East = new Direction("E", 1, 0);
    public static final Direction West = new Direction("W", -1, 0);

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

    public Direction getCounterDirection() {
        return this.getNextDirectionFromClockwise().getNextDirectionFromClockwise();
    }

    @Override
    public String toString()
    {
        return this.name;
    }


}
