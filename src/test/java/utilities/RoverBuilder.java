package utilities;

import org.marsrover.console.Logger;
import org.marsrover.planet.Planet;
import org.marsrover.planet.PlanetWithoutObstacles;
import org.marsrover.rover.Rover;
import org.marsrover.topology.Coordinates;
import org.marsrover.topology.Direction;

public class RoverBuilder {

    private Direction direction = Direction.North;
    private Planet planet = new PlanetWithoutObstacles(5, 5);
    private Coordinates coordinates = new Coordinates(1,2);
    private final Logger logger = new Logger();

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
        return new Rover(coordinates, direction, planet, logger);
    }
}
