package org.marsrover.abstract_class;

import org.marsrover.records.Coordinates;
import org.marsrover.models.Height;
import org.marsrover.models.Width;

public abstract class Planet
{
    private final Height height;
    private final Width width;

    protected Planet(Height height, Width width)
    {
        this.height = height;
        this.width = width;
    }

    protected Height getHeight()
    {
        return height;
    }

    protected Width getWidth()
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
