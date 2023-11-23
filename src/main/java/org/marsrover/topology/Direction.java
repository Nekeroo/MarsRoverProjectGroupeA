package org.marsrover.topology;

/**
 * Classe de Direction avec 4 constantes (North, South, East, West) utilisées pour la direction du Rover
 * @param name
 * @param xVector
 * @param yVector
 */
public record Direction(String name, int xVector, int yVector)
{
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

    @Override
    public String toString()
    {
        return this.name;
    }

    public static Direction mapDirectionFromString(String direction) {
        switch (direction) {
            case "N" -> {
                return North;
            }
            case "S" -> {
                return South;
            }
            case "E" -> {
                return East;
            }
            case "W" -> {
                return West;
            }
        }
        return null;
    }
}
