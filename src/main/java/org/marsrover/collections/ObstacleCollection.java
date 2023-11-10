package org.marsrover.collections;

import org.marsrover.records.Coordinates;
import org.marsrover.records.Obstacle;

import java.util.List;

public class ObstacleCollection {
    private final List<Obstacle> obstacles;

    public ObstacleCollection(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public boolean containsObstacleAtCoordinates(Coordinates coordinates) {
        return obstacles.stream().anyMatch(obstacle -> obstacle.coordinates().equals(coordinates));
    }
}
