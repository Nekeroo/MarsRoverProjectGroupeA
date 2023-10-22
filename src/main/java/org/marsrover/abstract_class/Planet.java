package org.marsrover.abstract_class;

import org.marsrover.records.Coordinates;

public abstract class Planet
{
    private final int height;
    private final int width;

    protected Planet(int height, int width)
    {
        this.height = height;
        this.width = width;
    }

    protected int getHeight()
    {
        return height;
    }

    protected int getWidth()
    {
        return width;
    }

    public Coordinates canonise(Coordinates coordinates)
    {
        return coordinates.moduloCoordinates(coordinates, height, width);
    }

    public boolean isThereObstacle(Coordinates coordinates)
    {
        return false;
    }
}
