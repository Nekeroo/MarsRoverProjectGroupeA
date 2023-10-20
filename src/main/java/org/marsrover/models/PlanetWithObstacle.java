package org.marsrover.models;

import org.marsrover.abstrat_class.Decorator;
import org.marsrover.abstrat_class.Planet;
import org.marsrover.records.Obstacle;

import java.util.List;

// Objet Valeur
public class PlanetWithObstacle extends Decorator {

    private final List<Obstacle> obstacles;

    public PlanetWithObstacle(Planet planet, List<Obstacle> obstacles) {
        super(planet);
        this.obstacles = obstacles;
    }

    public boolean isObstaclesInFrontOfRover(int x, int y)
    {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.coordinates().x() == x && obstacle.coordinates().y() == y)
                return true;
        }
        return false;
    }


}
