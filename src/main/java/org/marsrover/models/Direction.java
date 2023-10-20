package org.marsrover.models;

// Objet Valeur
public final class Direction {

    public static final  Direction North = new Direction("N", 0, 1);
    public static final Direction South = new Direction("S", 0, -1);
    public static final Direction East = new Direction("E", 1, 0);
    public static final Direction West = new Direction("W", -1, 0);
    private final String name;
    private final int xVector;
    private final int yVector;

    public Direction(String name, int xVector, int yVector)
    {
        this.name = name;
        this.xVector = xVector;
        this.yVector = yVector;
    }

    Direction getNextDirectionFromClockwise()
    {
        if (this.equals(North))
            return East;
        if (this.equals(East))
            return South;
        if (this.equals(South))
            return West;
        return North;
    }

    Direction getNextDirectionCounterClockwise()
    {
        return getNextDirectionFromClockwise().getNextDirectionFromClockwise().getNextDirectionFromClockwise();
    }

    public Direction getCounterDirection() {
        return this.getNextDirectionFromClockwise().getNextDirectionFromClockwise();
    }

    public int getVectorX()
    {
        return this.xVector;
    }

    public int getVectorY()
    {
        return this.yVector;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
