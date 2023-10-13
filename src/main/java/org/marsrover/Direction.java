package org.marsrover;

public class Direction {

    public static Direction North = new Direction("N", 0, 1);
    public static Direction South = new Direction("S", 0, -1);
    public static Direction East = new Direction("E", 1, 0);
    public static Direction West = new Direction("W", -1, 0);
    private String name;
    private int xVector;
    private int yVector;

    public Direction(String name, int xVector, int yVector)
    {
        this.name = name;
        this.xVector = xVector;
        this.yVector = yVector;
    }

    protected Direction getNextClockwise()
    {
        if (this.equals(North))
            return East;
        if (this.equals(East))
            return South;
        if (this.equals(South))
            return West;
        return North;
    }

    protected Direction getNextCounterClockwise()
    {
        return getNextClockwise().getNextClockwise().getNextClockwise();
    }

    protected int getVectorX()
    {
        return this.xVector;
    }

    protected int getVectorY()
    {
        return this.yVector;
    }
}
