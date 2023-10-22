package org.marsrover.models;
import org.marsrover.abstract_class.Planet;
import org.marsrover.records.Coordinates;
import org.marsrover.records.Direction;
import org.marsrover.records.Position;

public final class Rover {
    private final Position position;
    private final Planet planet;

    public Rover(Coordinates coordinates, Direction direction, Planet planet) {
        this.position = new Position(planet.canonise(coordinates), direction);
        this.planet = planet;
        System.out.printf("Coordonnées : " + this.getCurrentCoordinates());
    }

    public Direction getCurrentDirection() {
        return position.direction();
    }
    public Coordinates getCurrentCoordinates() {
        return position.coordinates();
    }

    public Rover turnRight() {
        return new Rover(this.getCurrentCoordinates(), this.getCurrentDirection().getNextDirectionFromClockwise(), this.planet);
    }

    public Rover turnLeft() {
        return new Rover(this.getCurrentCoordinates(), this.getCurrentDirection().getNextDirectionCounterClockwise(), this.planet);
    }

    public Rover moveForward() {
        Coordinates coordinates = this.getCurrentCoordinates().addCoordinates(this.getCurrentCoordinates(), this.getCurrentDirection());
        if (planet.isObstaclesAt(coordinates)) {
            System.out.println("Obstacle found");
            return this;
        }
        return new Rover(new Coordinates(coordinates.x(), coordinates.y()), this.getCurrentDirection(), this.planet);
    }

    public Rover moveBack() {
        Coordinates coordinates = this.getCurrentCoordinates().subCoordinates(this.getCurrentCoordinates(), this.getCurrentDirection());
        if (planet.isObstaclesAt(coordinates)) {
            System.out.println("Obstacle found");
            return this;
        }
        return new Rover(new Coordinates(coordinates.x(), coordinates.y()), this.getCurrentDirection(), this.planet);
    }
}