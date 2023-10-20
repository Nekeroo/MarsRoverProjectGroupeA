package org.marsrover.models;

import org.marsrover.abstract_class.PlanetDecorator;
import org.marsrover.abstract_class.Planet;
import org.marsrover.records.Coordinates;
import org.marsrover.records.Obstacle;

import java.util.List;

// Objet Valeur
public final class PlanetWithObstacle extends PlanetDecorator
{
    private final List<Obstacle> obstacles;

    public PlanetWithObstacle(Planet planet, List<Obstacle> obstacles)
    {
        super(planet);
        this.obstacles = obstacles;
    }

    @Override
    public boolean isObstaclesAt(Coordinates coordinates) {
        return obstacles.stream().anyMatch(obstacle -> obstacle.coordinates().equals(coordinates));
    }
}
