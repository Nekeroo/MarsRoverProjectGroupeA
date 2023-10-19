package org.marsrover.models;

import org.marsrover.abstrat_class.Decorator;
import org.marsrover.abstrat_class.PlanetBase;
import org.marsrover.records.Obstacle;

import java.util.List;

public class PlanetWithObstacle extends Decorator {

    private final List<Obstacle> obstacles;

    public PlanetWithObstacle(PlanetBase planetBase, List<Obstacle> obstacles) {
        super(planetBase);
        this.obstacles = obstacles;
    }

    public boolean checkObstacle(int x, int y)
    {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.coordinates().x() == x && obstacle.coordinates().y() == y)
                return true;
        }
        return false;
    }


}
