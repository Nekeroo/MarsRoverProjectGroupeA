package org.marsrover.planet;

import org.marsrover.topology.Coordinates;

import java.util.List;

/**
 * PlanetWithObstacle hérite de PlanetDecorator.
 */
public final class PlanetWithObstacle implements IPlanet
{

    private final int height;

    private final int width;

    private final List<Obstacle> obstacles;

    public PlanetWithObstacle(int height, int width, List<Obstacle> obstacles)
    {
        this.height = height;
        this.width = width;
        this.obstacles = obstacles;
    }


    @Override
    public Coordinates canonise(Coordinates coordinates)
    {
        return coordinates.moduloCoordinates(coordinates, height, width);
    }

    @Override
    public boolean isObstaclesAt(Coordinates coordinates)
    {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.coordinates().x() == coordinates.x() && obstacle.coordinates().y() == coordinates.y())
                return true;
        }
        return false;
    }
}
