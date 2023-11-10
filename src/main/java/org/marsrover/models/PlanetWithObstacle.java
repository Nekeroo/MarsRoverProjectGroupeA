package org.marsrover.models;

import org.marsrover.abstract_class.PlanetDecorator;
import org.marsrover.abstract_class.Planet;
import org.marsrover.collections.ObstacleCollection;
import org.marsrover.records.Coordinates;
import org.marsrover.records.Obstacle;

import java.util.List;

// Objet Valeur
public final class PlanetWithObstacle extends PlanetDecorator
{
    private final ObstacleCollection obstacleCollection;

    public PlanetWithObstacle(Planet planet, List<Obstacle> obstacles)
    {
        super(planet);
        this.obstacleCollection = new ObstacleCollection(obstacles);
    }

    @Override
    public boolean isThereObstacle(Coordinates coordinates) {
        return obstacleCollection.containsObstacleAtCoordinates(coordinates);
    }
}
