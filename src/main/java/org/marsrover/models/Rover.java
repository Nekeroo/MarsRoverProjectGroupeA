package org.marsrover.models;
import org.marsrover.abstract_class.Planet;
import org.marsrover.helpers.RoverHelper;
import org.marsrover.records.Coordinates;
import org.marsrover.records.Direction;
import org.marsrover.records.Position;

public final class Rover {
    private final Position position;
    private final Planet planet;

    public Rover(Coordinates coordinates, Direction direction, Planet planet) {
        this.position = new Position(planet.canonise(coordinates), direction);
        this.planet = planet;
        System.out.printf("Coordinates : %s%n", getCurrentCoordinates());
    }

    public Direction getCurrentDirection() {
        return position.direction();
    }
    public Coordinates getCurrentCoordinates() {
        return position.coordinates();
    }

    private Rover turn(boolean clockwise) {
        return new Rover(getCurrentCoordinates(), RoverHelper.rotateDirection(getCurrentDirection(), clockwise), planet);
    }
    private Rover move(boolean forward) {
        Coordinates newCoordinates = RoverHelper.calculateNewCoordinates(getCurrentCoordinates(), getCurrentDirection(), forward);
        if (planet.isThereObstacle(newCoordinates)) {
            System.out.println("Obstacle found");
            return this;
        }
        return new Rover(new Coordinates(newCoordinates.x(), newCoordinates.y()), this.getCurrentDirection(), this.planet);
    }

    public Rover turnRight() {
        return turn(true);
    }
    public Rover turnLeft() {
        return turn(false);
    }
    public Rover moveForward() {
        return move(true);
    }
    public Rover moveBack() {
        return move(false);
    }
}