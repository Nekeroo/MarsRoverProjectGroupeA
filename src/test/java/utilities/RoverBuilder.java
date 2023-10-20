package utilities;

import org.marsrover.abstract_class.Planet;
import org.marsrover.models.Direction;
import org.marsrover.models.PlanetWithoutObstacles;
import org.marsrover.models.Rover;
import org.marsrover.records.Coordinates;

public class RoverBuilder {

    private Direction direction = Direction.North;
    private Planet planet = new PlanetWithoutObstacles(5, 5);
    private Coordinates coordinates = new Coordinates(1,2);

    public RoverBuilder looking(Direction direction) {
        this.direction = direction;
        return this;
    }

    public RoverBuilder onThisPosition(int x, int y) {
        this.coordinates = new Coordinates(x, y);
        return this;
    }

    public RoverBuilder onPlanet(Planet planet) {
        this.planet = planet;
        return this;
    }

    public Rover build() {
        return new Rover(coordinates, direction, planet);
    }
}