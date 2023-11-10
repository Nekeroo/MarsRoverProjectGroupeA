package org.marsrover.planet;

import org.marsrover.topologie.Coordinates;

import java.util.List;

/**
 * PlanetWithObstacle hérite de PlanetDecorator.
 */
public final class PlanetWithObstacle extends PlanetDecorator
{
    private final List<Obstacle> obstacles;

    public PlanetWithObstacle(Planet planet, List<Obstacle> obstacles)
    {
        super(planet);
        this.obstacles = obstacles;
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
