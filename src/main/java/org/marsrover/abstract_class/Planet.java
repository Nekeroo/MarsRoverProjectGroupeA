package org.marsrover.abstract_class;

import org.marsrover.records.Coordinates;

public abstract class Planet
{
    private final Integer height;
    private final Integer width;

    protected Planet(Integer height, Integer width)
    {
        this.height = height;
        this.width = width;
    }

    protected Integer getHeight()
    {
        return height;
    }

    protected Integer getWidth()
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
