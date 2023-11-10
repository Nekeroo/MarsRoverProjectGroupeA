package org.marsrover.collections;

import org.marsrover.records.Coordinates;
import org.marsrover.records.Obstacle;
import java.util.List;
import java.util.stream.Stream;

public class ObstacleCollection {
    private final List<Obstacle> obstacles;

    public ObstacleCollection(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public boolean containsObstacleAtCoordinates(Coordinates coordinates) {
        Stream<Coordinates> obstacleCoordinates = obstacles.stream().map(Obstacle::coordinates);
        return obstacleCoordinates.anyMatch(coord -> coord.equals(coordinates));
    }
}
