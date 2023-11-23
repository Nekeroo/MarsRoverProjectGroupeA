package org.marsrover.planet;

import org.marsrover.topology.Coordinates;

/**
 * PlanetWithoutObstacles hérite de Planet
 */
public final class PlanetWithoutObstacles implements IPlanet
{
    private final int height;

    private final int width;

    public PlanetWithoutObstacles(int height, int width)
    {
        this.height = height;
        this.width = width;
    }

    @Override
    public Coordinates canonise(Coordinates coordinates)
    {
        return coordinates.moduloCoordinates(coordinates, height, width);
    }

    @Override
    public boolean isObstaclesAt(Coordinates coordinates) {
        return false;
    }
}
